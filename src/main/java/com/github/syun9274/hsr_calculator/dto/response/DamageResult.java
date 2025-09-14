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
    public static DamageResult result(Map<DamageType, Integer> finalDamage) {
        return DamageResult.builder()
                .basicAttackNormal(finalDamage.get(DamageType.BASIC_NORMAL))
                .basicAttackCrit(0)
                .basicAttackExpected(0)
                .skillNormal(0)
                .skillCrit(0)
                .skillExpected(0)
                .ultimateNormal(0)
                .ultimateCrit(0)
                .ultimateExpected(0)
                .build();
    }
}