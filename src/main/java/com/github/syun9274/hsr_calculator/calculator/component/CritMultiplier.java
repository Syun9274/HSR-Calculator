package com.github.syun9274.hsr_calculator.calculator.component;

import com.github.syun9274.hsr_calculator.calculator.formula.DamageFormula;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CritMultiplier {

    /*
    Crit Multiplier = 1 + (Crit Rate × Crit DMG)

    결과를 보여줄 때
    1. 치명타 x
    2. 치명타 o
    3. 평균 기대값 데미지
     */

    public double getCritMultiplier(List<BuffDto> buffDtos) {

        double critRate = DamageFormula.DEFAULT_CRIT_RATE +
                MathUtil.sumPercentBuffs(buffDtos, BuffType.CRIT_RATE_PERCENT);
        double critDamage = DamageFormula.DEFAULT_CRIT_DMG +
                MathUtil.sumPercentBuffs(buffDtos, BuffType.CRIT_DMG_PERCENT);

        critRate = Math.min(critRate, DamageFormula.MAX_CRIT_RATE);
        return 1 + (critRate * critDamage);
    }

    public double getCritMultiplier(List<BuffDto> buffDtos, boolean isCrit) {

        double critDamage = DamageFormula.DEFAULT_CRIT_DMG +
                MathUtil.sumPercentBuffs(buffDtos, BuffType.CRIT_DMG_PERCENT);

        if (isCrit) {
            return 1 + critDamage;
        } else {
            return 1;
        }
    }

    public double getCritMultiplierDefault() {
        return 1 + (DamageFormula.DEFAULT_CRIT_RATE * DamageFormula.DEFAULT_CRIT_DMG);
    }
}
