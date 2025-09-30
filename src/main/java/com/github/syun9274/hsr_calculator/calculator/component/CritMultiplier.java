package com.github.syun9274.hsr_calculator.calculator.component;

import com.github.syun9274.hsr_calculator.calculator.formula.DamageFormula;
import org.springframework.stereotype.Component;

@Component
public class CritMultiplier {

    /*
    Crit Multiplier = 1 + (Crit Rate × Crit DMG)

    결과를 보여줄 때
    1. 치명타 o
    2. 치명타 x
    3. 평균 기대값 데미지
     */

    public double getCritMultiplier(double critRate, double critDamage) {
        critRate = Math.min(critRate, DamageFormula.MAX_CRIT_RATE);
        return 1 + (critRate * critDamage);
    }

    public double getCritMultiplierDefault() {
        return 1 + (DamageFormula.DEFAULT_CRIT_RATE * DamageFormula.DEFAULT_CRIT_DMG);
    }
}
