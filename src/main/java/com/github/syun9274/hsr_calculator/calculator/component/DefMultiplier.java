package com.github.syun9274.hsr_calculator.calculator.component;

import com.github.syun9274.hsr_calculator.calculator.formula.DamageFormula;
import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DefMultiplier {

    /*
    방어력 보정 요소 (적 방어력)

    DEF Multiplier scales with the level difference between characters, similar to Genshin Impact.
    The higher the attacker level compared to defender level, the greater the DEF Multiplier.

    The DEF-related equations for a normal defender are:
    DEF Multiplier = 100% - [DEF / (DEF + 200 + 10 * Attacker Level)]

    To find the DEF, you need to use another formula:
    DEF = Base DEF * (100% + DEF% - (DEF Reduction + DEF Ignore)) + Flat DEF

    DEF cannot go below 0.
     */

    /**
     * 방어력 배수 계산
     *
     * @param character     공격하는 캐릭터 (레벨 정보 필요)
     * @param enemy         방어하는 적 (기본 방어력 정보 필요)
     * @param charBuffs  아군이 받고 있는 버프 (방어력 무시)
     * @param enemyBuffs 적이 받고 있는 버프 (방어력 증가, 방어력 감소)
     * @return 방어력으로 인한 데미지 감소 배수 (0 ~ 1 사이 값)
     */
    public double getDefMultiplier(CharacterDto character, EnemyDto enemy,
                                   List<Buff> charBuffs, List<Buff> enemyBuffs) {

        double def = calculateDef(enemy, charBuffs, enemyBuffs);

        return 1 - (def / (def + 200 + 10 * character.level()));
    }

    /**
     * 적의 실제 방어력 계산
     * Base DEF × (1 + DEF% - DEF감소%) + Flat DEF
     *
     * @param enemy         적 정보
     * @param enemyBuffs 적 버프 (방어력 증가, 방어력 감소, 방어력 무시)
     * @return 계산된 실제 방어력 (최소값 보장)
     */
    private double calculateDef(EnemyDto enemy, List<Buff> charBuffs, List<Buff> enemyBuffs) {
        int baseDef = enemy.baseDef();
        double defPer = calculateDefPer(enemyBuffs);
        double defFlat = calculateDefFlat(enemyBuffs);
        double defReduction = calculateDefReduction(charBuffs, enemyBuffs);

        double def = baseDef * (1 + defPer - defReduction) + defFlat;
        return Math.max(DamageFormula.MIN_DEF, def);
    }

    /**
     * 적의 방어력 퍼센트 버프 합계
     */
    private double calculateDefPer(List<Buff> buffs) {
        return MathUtil.sumPercentBuffs(buffs, BuffType.DEF_PERCENT);
    }

    /**
     * 적의 방어력 고정 버프 합계
     */
    private double calculateDefFlat(List<Buff> buffs) {
        return MathUtil.sumFlatBuffs(buffs, BuffType.DEF_FLAT);
    }

    /**
     * 캐릭터의 방어력 감소/무시 효과 합계
     */
    private double calculateDefReduction(List<Buff> charBuffs, List<Buff> enemyBuffs) {
        return MathUtil.sumPercentBuffs(charBuffs, BuffType.DEF_IGNORE)
                + MathUtil.sumPercentBuffs(enemyBuffs, BuffType.DEF_REDUCTION);
    }

}
