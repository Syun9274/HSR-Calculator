package com.github.syun9274.hsr_calculator.dto.character;

import com.github.syun9274.hsr_calculator.model.character.BasicAttack;

public record BasicAttackDto(
        double skillMultiplier,
        double extraMultiplier,
        int extraDamage
) {
    public static BasicAttackDto from(BasicAttack entity) {
        return new BasicAttackDto(
                entity.getSkillMultiplier(),
                entity.getExtraMultiplier(),
                entity.getExtraDamage()
        );
    }
}