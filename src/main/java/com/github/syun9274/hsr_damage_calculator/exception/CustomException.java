package com.github.syun9274.hsr_damage_calculator.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final int code;
    private final String message;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
