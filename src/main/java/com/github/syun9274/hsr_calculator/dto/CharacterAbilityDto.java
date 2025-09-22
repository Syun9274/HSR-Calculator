package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.CharacterAbility;
import com.github.syun9274.hsr_calculator.model.enums.AbilityType;
import lombok.Builder;

// TODO: 전투 스킬, 필살기가 버프/디버프인 서포터 캐릭터도 사용 가능하도록 개선 필요
@Builder
public record CharacterAbilityDto(
        AbilityType abilityType,
        double skillMultiplier,
        double extraMultiplier,
        int extraDamage
) {
    public static CharacterAbilityDto from(CharacterAbility entity) {
        return new CharacterAbilityDto(
                entity.getAbilityType(),
                entity.getSkillMultiplier(),
                entity.getExtraMultiplier(),
                entity.getExtraDamage()
        );
    }
}