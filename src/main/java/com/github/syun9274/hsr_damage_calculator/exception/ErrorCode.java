package com.github.syun9274.hsr_damage_calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SERVER_ERROR(500, "내부 오류가 발생했습니다."),
    ;

    private final int code;
    private final String message;

}
