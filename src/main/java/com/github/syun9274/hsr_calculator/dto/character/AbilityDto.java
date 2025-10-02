package com.github.syun9274.hsr_calculator.dto.character;

import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.EffectType;

import java.util.List;

public record AbilityDto(
        EffectType effectType,
        Double skillMultiplier,
        Double extraMultiplier,
        Integer extraDamage,
        List<BuffDto> buffs
) {

}
