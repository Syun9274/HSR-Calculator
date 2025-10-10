package com.github.syun9274.hsr_calculator.calculator;

import com.github.syun9274.hsr_calculator.calculator.component.*;
import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.character.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.exception.CustomException;
import com.github.syun9274.hsr_calculator.exception.ErrorCode;
import com.github.syun9274.hsr_calculator.model.enums.DamageType;
import com.github.syun9274.hsr_calculator.model.enums.EffectType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
    private final DmgTakenMultiplier dmgTakenMultiplier;
    private final ResMultiplier resMultiplier;
    private final UniversalDmgReductionMultiplier universalDmgReductionMultiplier;
    private final WeakenMultiplier weakenMultiplier;

    public Map<DamageType, Integer> calculateFinalDamage(CharacterDto character,
                                                         EnemyDto enemy,
                                                         List<Buff> charBuffs,
                                                         List<Buff> enemyBuffs,
                                                         boolean isBroken) {
        try {
            // 공격 타입에 따라 달라지는 변수를 제외한 나머지 계산
            double commonMultiplier = calculateCommonArgument(
                    character, enemy,
                    charBuffs, enemyBuffs,
                    isBroken);

            // 계산에 사용되는 캐릭터 스탯 값 - 버프 적용 후
            double scalingAttribute = getScalingAttributeAfterBuffs(character, charBuffs);
            log.info("Scaling attribute: {}", scalingAttribute);

            Map<DamageType, Integer> finalDamage = new HashMap<>();

            finalDamage.putAll(calculateBasicAttackDamages(commonMultiplier, scalingAttribute, character, charBuffs));
            finalDamage.putAll(calculateSkillDamages(commonMultiplier, scalingAttribute, character, charBuffs));
            finalDamage.putAll(calculateUltimateDamages(commonMultiplier, scalingAttribute, character, charBuffs));

            return finalDamage;

        } catch (ArithmeticException e) {
            log.error("Damage calculation overflow: {}", e.getMessage());
            throw new CustomException(ErrorCode.DAMAGE_CALCULATION_OVERFLOW);

        } catch (Exception e) {
            log.error("Damage calculation error: {}", e.getMessage());
            throw new CustomException(ErrorCode.CALCULATION_ERROR);
        }
    }

    /**
     * 스킬 타입에 관계없이 공통으로 적용되는 데미지 배수를 계산한다.
     * </p>
     * 방어력, 피해 증가, 저항, 범용 피해 감소, 약화 배수를 포함한다.
     */
    private double calculateCommonArgument(CharacterDto character,
                                           EnemyDto enemy,
                                           List<Buff> charBuffs,
                                           List<Buff> enemyBuffs,
                                           boolean isBroken) {
        // 공격 타입에 따라 달라지는 변수를 제외한 나머지 계산
        double def = defMultiplier.getDefMultiplier(character, enemy, charBuffs, enemyBuffs);
        double dmgTaken = dmgTakenMultiplier.getDmgTakenMultiplier(enemyBuffs);
        double res = resMultiplier.getResMultiplier(character, enemy, charBuffs, enemyBuffs);
        double uniDmgRed = universalDmgReductionMultiplier.getUniversalDmgReductionMultiplier(isBroken);
        double weak = weakenMultiplier.getWeakenMultiplier(enemyBuffs);

        return def * dmgTaken * res * uniDmgRed * weak;
    }

    /**
     * 캐릭터의 스케일링 스탯을 버프 적용 후 값으로 계산한다.
     * </p>
     * 캐릭터의 스케일링 속성(공격력/체력/방어력)에 따라 해당 스탯을 반환한다.
     */
    private double getScalingAttributeAfterBuffs(CharacterDto character, List<Buff> buffs) {
        // 캐릭터 스탯에 버프 일괄 적용
        Map<StatType, Double> finalStats = statCalculator.calculateFinalStats(
                character.baseHp(),
                character.baseAtk(),
                character.baseDef(),
                buffs);

        // 계산에 사용되는 캐릭터 스탯 추출
        return switch (character.scalingAttribute()) {
            case StatType.HP -> finalStats.get(StatType.HP);
            case StatType.DEF -> finalStats.get(StatType.DEF);
            default -> finalStats.get(StatType.ATK);
        };
    }

    private Map<DamageType, Integer> calculateBasicAttackDamages(double commonMultiplier,
                                                                 double scalingAttribute,
                                                                 CharacterDto character,
                                                                 List<Buff> charBuffs) {
        Map<DamageType, Integer> results = new HashMap<>();

        commonMultiplier *= dmgMultiplier.getBasicAttackDmgMultiplier(character.element(), charBuffs);
        commonMultiplier *= baseDmg.getBaseDmg(
                character.basicAttack().skillMultiplier(),
                character.basicAttack().extraMultiplier(),
                scalingAttribute,
                character.basicAttack().extraDamage());

        results.put(DamageType.BASIC_NORMAL,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs, false)));

        results.put(DamageType.BASIC_CRIT,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs, true)));

        results.put(DamageType.BASIC_EXPECTED,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs)));

        return results;
    }

    private Map<DamageType, Integer> calculateSkillDamages(double commonMultiplier,
                                                           double scalingAttribute,
                                                           CharacterDto character,
                                                           List<Buff> charBuffs) {
        if (character.skill().effectType() != EffectType.DAMAGE) {
            return Map.of(
                    DamageType.SKILL_NORMAL, 0,
                    DamageType.SKILL_CRIT, 0,
                    DamageType.SKILL_EXPECTED, 0
            );
        }

        Map<DamageType, Integer> results = new HashMap<>();

        commonMultiplier *= dmgMultiplier.getSkillDmgMultiplier(character.element(), charBuffs);
        commonMultiplier *= baseDmg.getBaseDmg(
                character.skill().damageInfo().skillMultiplier(),
                character.skill().damageInfo().extraMultiplier(),
                scalingAttribute,
                character.skill().damageInfo().extraDamage());

        results.put(DamageType.SKILL_NORMAL,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs, false)));

        results.put(DamageType.SKILL_CRIT,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs, true)));

        results.put(DamageType.SKILL_EXPECTED,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs)));

        return results;
    }

    private Map<DamageType, Integer> calculateUltimateDamages(double commonMultiplier,
                                                              double scalingAttribute,
                                                              CharacterDto character,
                                                              List<Buff> charBuffs) {
        if (character.ultimate().effectType() != EffectType.DAMAGE) {
            return Map.of(
                    DamageType.ULTIMATE_NORMAL, 0,
                    DamageType.ULTIMATE_CRIT, 0,
                    DamageType.ULTIMATE_EXPECTED, 0
            );
        }

        Map<DamageType, Integer> results = new HashMap<>();

        commonMultiplier *= dmgMultiplier.getUltimateDmgMultiplier(character.element(), charBuffs);
        commonMultiplier *= baseDmg.getBaseDmg(
                character.ultimate().damageInfo().skillMultiplier(),
                character.ultimate().damageInfo().extraMultiplier(),
                scalingAttribute,
                character.ultimate().damageInfo().extraDamage());

        results.put(DamageType.ULTIMATE_NORMAL,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs, false)));

        results.put(DamageType.ULTIMATE_CRIT,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs, true)));

        results.put(DamageType.ULTIMATE_EXPECTED,
                MathUtil.toGameDamageInt(
                        commonMultiplier *
                        critMultiplier.getCritMultiplier(charBuffs)));

        return results;
    }
}
