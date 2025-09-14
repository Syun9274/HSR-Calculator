package com.github.syun9274.hsr_damage_calculator.calculator;

import com.github.syun9274.hsr_damage_calculator.calculator.component.*;
import com.github.syun9274.hsr_damage_calculator.exception.CustomException;
import com.github.syun9274.hsr_damage_calculator.exception.ErrorCode;
import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.Character;
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
    private final DmgTakenMultiplier dmgTakenMultiplier;
    private final ResMultiplier resMultiplier;
    private final UniversalDmgReductionMultiplier universalDmgReductionMultiplier;
    private final WeakenMultiplier weakenMultiplier;

    public Map<String, Integer> calculateOutgoingDmg(Character character,
                                                     Enemy enemy,
                                                     List<Buff> charBuffs,
                                                     List<Buff> enemyBuffs,
                                                     boolean isBroken) {
        try {
            double outGoingDamage = 0.0;

            // 공격 타입에 따라 달라지는 변수를 제외한 나머지 계산
            double def = defMultiplier.getDefMultiplier(character, enemy, enemyBuffs);
            double dmgTaken = dmgTakenMultiplier.getDmgTakenMultiplier(enemyBuffs);
            double res = resMultiplier.getResMultiplier(character, enemy, charBuffs);
            double uniDmgRed = universalDmgReductionMultiplier.getUniversalDmgReductionMultiplier(isBroken);
            double weak = weakenMultiplier.getWeakenMultiplier(enemyBuffs);

            outGoingDamage = def * dmgTaken * res * uniDmgRed * weak;

            // 캐릭터 스탯에 버프 일괄 적용
            Map<String, Double> finalStats = statCalculator.calculateFinalStats(
                    character.getBaseHp(),
                    character.getBaseAtk(),
                    character.getBaseDef(),
                    charBuffs);

            // 계산에 사용되는 캐릭터 스탯 추출
            double scalingAttribute = switch (character.getScalingAttribute().toLowerCase()) {
                case "hp" -> finalStats.get("Hp");
                case "def" -> finalStats.get("Def");
                default -> finalStats.get("Atk");
            };

            double finalDamage = outGoingDamage *
                    baseDmg.getBaseDmg(
                            character.getBasicAttack().getSkillMultiplier(),
                            character.getBasicAttack().getExtraMultiplier(),
                            scalingAttribute,
                            character.getBasicAttack().getExtraDamage()) *
                    dmgMultiplier.getBasicAttackDmgMultiplier(charBuffs);

            return Map.of(
                    "Final Damage", MathUtil.toGameDamageInt(finalDamage));

        } catch (ArithmeticException e) {
            log.error("Damage calculation overflow: {}", e.getMessage());
            throw new CustomException(ErrorCode.DAMAGE_CALCULATION_OVERFLOW);

        } catch (Exception e) {
            log.error("Damage calculation error: {}", e.getMessage());
            throw new CustomException(ErrorCode.CALCULATION_ERROR);
        }
    }
}
