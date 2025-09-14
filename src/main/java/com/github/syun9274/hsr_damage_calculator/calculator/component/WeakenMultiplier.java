package com.github.syun9274.hsr_damage_calculator.calculator.component;

import com.github.syun9274.hsr_damage_calculator.dto.BuffDto;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_damage_calculator.util.MathUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeakenMultiplier {

    /*
    적군 스스로 약화 디버프 (나타샤나 삼포의 특성, 또는 적 스스로 약화)

    The Weaken Multiplier is related to the Weaken status effect
    that can be applied by Natasha and Sampo’s passive Trace.
    At present, it is only applicable when calculating enemy damage
    but may be relevant in the future if enemies can inflict Weaken:
    Weaken Multiplier = 100% - Weaken%
     */

    public double getWeakenMultiplier(List<BuffDto> buffDtos) {
        return 1 - calculateWeakenMultiplier(buffDtos);
    }

    private double calculateWeakenMultiplier(List<BuffDto> buffDtos) {
        return MathUtil.sumPercentBuffs(buffDtos, BuffType.WEAKEN);
    }
}
