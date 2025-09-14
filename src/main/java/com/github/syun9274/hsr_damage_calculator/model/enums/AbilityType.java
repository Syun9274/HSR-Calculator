package com.github.syun9274.hsr_damage_calculator.model.enums;

public enum AbilityType {

    BASIC("일반 공격"),
    SKILL("전투 스킬"),
    ULTIMATE("필살기"),
    TRACE("행적 효과")
    ;

    final String krDescription;

    AbilityType(String krDescription) {
        this.krDescription = krDescription;
    }
}
