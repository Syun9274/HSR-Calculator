package com.github.syun9274.hsr_damage_calculator.calculator.component;

import com.github.syun9274.hsr_damage_calculator.calculator.formula.DamageFormula;
import org.springframework.stereotype.Component;

@Component
public class UniversalDmgReductionMultiplier {

    /*
    데미지 감소 요소 (약점)

    This portion of the equation is as follows:
    Universal DMG Reduction Multiplier = 100% * (1 - DMG Reduction_1) * (1 - DMG Reduction_2) * ...

    When an enemy has Toughness, they have 10% Universal DMG Reduction,
    which is reduced to 0% when broken.
    Note this multiplier stacks multiplicative with other sources.
     */

    public double getUniversalDmgReductionMultiplier(boolean isBroken) {
        return weaknessMultiplier(isBroken);
    }

    /**
     * 강인성이 존재할 경우 10% 데미지 저항
     *
     * @param isBroken 격파 여부 (격파 시 true)
     * @return 약점이 격파되지 않았을 경우 10% 저항 -> 0.9 반환
     */
    private double weaknessMultiplier(boolean isBroken) {
        if (isBroken) {
            return 1;
        } else {
            return 1 - DamageFormula.TOUGHNESS_DAMAGE_REDUCTION;
        }
    }
}
