package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import com.github.syun9274.hsr_calculator.model.enums.Target;

public record Buff(
        BuffType buffType,
        Double buffValue,
        StatType sourceStatType,
        Double perValue,
        Double increaseValue,
        Double maxValue,
        Integer maxStackCount,
        Target buffTarget
) {
    /**
     * 행적 버프
     */
    public Buff(BuffType buffType, Double buffValue) {
        this(buffType, buffValue,
                null, null, null, null,
                1, Target.SELF);
    }

    public Buff(BuffType buffType, Double buffValue, Target buffTarget) {
        this(buffType, buffValue,
                null, null, null, null,
                1, buffTarget);
    }

    public Buff(BuffType buffType, Double buffValue,
                Integer maxStackCount, Target buffTarget) {
        this(buffType, buffValue,
                null, null, null, null,
                maxStackCount, buffTarget);
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
     * @param buffTarget     SELF, ALLY, ENEMY
     */
    public Buff(BuffType buffType, Double buffValue,
                StatType sourceStatType, Double perValue, Double increaseValue, Double maxValue,
                Target buffTarget) {
        this(buffType, null,
                sourceStatType, perValue, increaseValue, maxValue,
                1, buffTarget);
    }
}
