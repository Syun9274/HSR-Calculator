package com.github.syun9274.hsr_calculator.dto;

import jakarta.persistence.Embeddable;

@Embeddable
public record DamageInfo(
        Double skillMultiplier,
        Double extraMultiplier,
        Integer extraDamage
) {
    // 스킬 배율만 있는 경우
    public DamageInfo(Double skillMultiplier) {
        this(skillMultiplier, null, null);
    }

    public DamageInfo(Double skillMultiplier, Double extraMultiplier) {
        this(skillMultiplier, extraMultiplier, null);
    }
}
