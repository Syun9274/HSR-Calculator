package com.github.syun9274.hsr_damage_calculator.calculator;

import com.github.syun9274.hsr_damage_calculator.calculator.component.*;
import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.Enemy;
import com.github.syun9274.hsr_damage_calculator.util.MathUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class DamageCalculator {

    private final StatCalculator statCalculator;
    private final BaseDmg baseDmg;
    private final CritMultiplier critMultiplier;
    private final DefMultiplier defMultiplier;
    private final DmgMultiplier dmgMultiplier;
    private final ResMultiplier resMultiplier;
    private final UniversalDmgReductionMultiplier universalDmgReductionMultiplier;
    private final WeakenMultiplier weakenMultiplier;

    // Outgoing DMG = Base DMG * DMG% Multiplier * DEF Multiplier * RES Multiplier * DMG Taken Multiplier * Universal DMG Reduction Multiplier * Weaken Multiplier
    // TODO: 대략적인 코드 진행 방식만 구현,
    public Map<String, Integer> calculateOutgoingDmg(Character character, Enemy enemy, List<Buff> buffs, boolean isBroken) {

        try {
            double outGoingDamage = 0.0;

            // 캐릭터 스탯에 버프 일괄 적용
            Map<String, Integer> finalStats = statCalculator.calculateFinalStats(
                    character.getBaseHp(),
                    character.getBaseAtk(),
                    character.getBaseDef(),
                    buffs);

            int hp = finalStats.get("Hp");
            int atk = finalStats.get("Atk");
            int def = finalStats.get("Def");

            // 공격 타입에 따라 달라지는 변수를 제외한 나머지 계산
            double res = resMultiplier.getResMultiplier(resPercent, buffs);
            double uniDmgRed = universalDmgReductionMultiplier.getUniversalDmgReductionMultiplier(isBroken);
            double weak = weakenMultiplier.getWeakenMultiplier(buffs);
            ...

            // 고정 변수 먼저 계산
            outGoingDamage = res * uniDmgRed * weak * ...;

            // 공격 타입 개별 계산 - 일반 공격
            double basicAttackDamage = outGoingDamage * baseDmg.getBaseDmg() * dmgMultiplier.getBasicAttackDmgMultiplier(buffs);

            // 전투 스킬
            double skillDamage = outGoingDamage * baseDmg.getBaseDmg() * dmgMultiplier.getSkillDmgMultiplier(buffs);

            // 필살기
            double ultimateDamage = outGoingDamage * baseDmg.getBaseDmg() * dmgMultiplier.getUltimateDmgMultiplier(buffs);

            return Map.of(
                    "Basic Attack", MathUtil.toGameDamageInt(basicAttackDamage),
                    "Skill", MathUtil.toGameDamageInt(skillDamage),
                    "Ultimate", MathUtil.toGameDamageInt(ultimateDamage));
        } catch (Exception e) {
            log.error("calculateOutgoingDmg Error: {}", e.getMessage());
            return Map.of("Exception", 500);
        }
    }
}
