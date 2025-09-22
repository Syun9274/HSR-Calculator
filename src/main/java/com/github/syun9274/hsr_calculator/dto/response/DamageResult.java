package com.github.syun9274.hsr_calculator.dto.response;

import com.github.syun9274.hsr_calculator.model.enums.DamageType;
import lombok.Builder;

import java.util.Map;

@Builder
public record DamageResult(
        int basicAttackNormal,
        int basicAttackCrit,
        int basicAttackExpected,
        int skillNormal,
        int skillCrit,
        int skillExpected,
        int ultimateNormal,
        int ultimateCrit,
        int ultimateExpected
) {
    public static DamageResult from(Map<DamageType, Integer> finalDamage) {
        return new DamageResult(
                finalDamage.getOrDefault(DamageType.BASIC_NORMAL, 0),
                finalDamage.getOrDefault(DamageType.BASIC_CRIT, 0),
                finalDamage.getOrDefault(DamageType.BASIC_EXPECTED, 0),
                finalDamage.getOrDefault(DamageType.SKILL_NORMAL, 0),
                finalDamage.getOrDefault(DamageType.SKILL_CRIT, 0),
                finalDamage.getOrDefault(DamageType.SKILL_EXPECTED, 0),
                finalDamage.getOrDefault(DamageType.ULTIMATE_NORMAL, 0),
                finalDamage.getOrDefault(DamageType.ULTIMATE_CRIT, 0),
                finalDamage.getOrDefault(DamageType.ULTIMATE_EXPECTED, 0)
        );
    }
}