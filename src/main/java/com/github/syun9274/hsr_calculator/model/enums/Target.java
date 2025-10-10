package com.github.syun9274.hsr_calculator.model.enums;

public enum Target {

    SELF("자버프"),
    ALLY("아군 버프"),
    ENEMY("적 지정 디버프"),
    ;

    final String krDescription;

    Target(String krDescription) {
        this.krDescription = krDescription;
    }
}
