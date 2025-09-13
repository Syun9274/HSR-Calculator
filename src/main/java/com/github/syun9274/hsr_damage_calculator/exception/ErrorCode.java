package com.github.syun9274.hsr_damage_calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 서버 내부 오류
    SERVER_ERROR(500, "내부 오류가 발생했습니다."),

    // 계산 관련 오류
    CALCULATION_ERROR(400, "데미지 계산 중 오류가 발생했습니다."),
    INVALID_STAT_VALUE(400, "잘못된 스탯 값입니다."),
    INVALID_BUFF_VALUE(400, "잘못된 버프 값입니다."),
    MISSING_CHARACTER_DATA(400, "캐릭터 데이터가 누락되었습니다."),
    MISSING_ENEMY_DATA(400, "적 데이터가 누락되었습니다."),

    // 수치 범위 오류
    STAT_OUT_OF_RANGE(400, "스탯 값이 허용 범위를 벗어났습니다."),
    DAMAGE_CALCULATION_OVERFLOW(400, "데미지 계산 결과가 범위를 초과했습니다."),
    DIVISION_BY_ZERO(500, "0으로 나누기 오류가 발생했습니다."),
    ;

    private final int code;
    private final String message;

}
