package com.github.syun9274.hsr_calculator.model.enums;

public enum CalculationType {

    PERCENTAGE("캐릭터 스탯의 %"),
    FIXED_VALUE("고정 값"),
    ;

    final String krDescription;

    CalculationType(String krDescription) {
        this.krDescription = krDescription;
    }
}
