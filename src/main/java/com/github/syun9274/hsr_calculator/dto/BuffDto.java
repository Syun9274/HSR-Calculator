package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.enums.BuffType;

public record BuffDto(
        BuffType buffType, // 버프 타입 (ex. 가하는 피해 증가)
        double buffValue   // 버프 수치 (백분율 형태 (ex. 10.0))
) {

}
