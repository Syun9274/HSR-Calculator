package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;

public record Buff(
        BuffType buffType,
        Double buffValue,
        StatType sourceStatType,
        Double perValue,
        Double increaseValue,
        Double maxValue,
        Integer currentStackCount,
        Integer maxStackCount
) {
    // 일반 버프 생성자
    public Buff(BuffType buffType, Double buffValue,
                Integer currentStackCount, Integer maxStackCount) {
        this(buffType, buffValue,
                null, null, null, null,
                currentStackCount, maxStackCount);
    }

    /**
     * 조건부 버프 생성자
     * </p>
     * 예시 - 체력 1000당 공격력 0.8% 증가 (최대 80%)
     *
     * @param buffType       ATK_PERCENT
     * @param sourceStatType HP
     * @param perValue       1000
     * @param increaseValue  0.8
     * @param maxValue       80
     */
    public Buff(BuffType buffType, StatType sourceStatType,
                Double perValue, Double increaseValue, Double maxValue) {
        this(buffType, null,
                sourceStatType, perValue, increaseValue, maxValue,
                1, 1);
    }
}
