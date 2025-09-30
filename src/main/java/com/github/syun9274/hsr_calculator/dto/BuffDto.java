package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;

public record BuffDto(
        BuffType buffType, // 버프 타입 (ex. 가하는 피해 증가)
        Double buffValue,  // 버프 수치 (백분율 형태 (ex. 10.0))
        StatType sourceStatType,
        Double sourceMultiplier
) {
    /**
     * 일반적인 상황의 BuffDto
     */
    public BuffDto(BuffType buffType, double buffValue) {
        this(buffType, buffValue, null, null);
    }

    /**
     * 동적 계산이 필요한 버프인 경우
     * </p>
     * (예. 특정 캐릭터 효과 명중의 25%만큼 다른 아군의 공격력 증가)
     *
     * @param buffType         ATK_FLAT
     * @param sourceStatType   EFFECT_HIT
     * @param sourceMultiplier 0.25
     */
    public BuffDto(BuffType buffType, StatType sourceStatType, Double sourceMultiplier) {
        this(buffType, null, sourceStatType, sourceMultiplier);
    }
}
