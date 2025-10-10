package com.github.syun9274.hsr_calculator.dto.character;

import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.DamageInfo;
import com.github.syun9274.hsr_calculator.model.enums.EffectType;

import java.util.List;

public record AbilityDto(
        EffectType effectType,
        DamageInfo damageInfo,
        List<Buff> buffs
) {

}
