package com.github.syun9274.hsr_calculator.model.enums;

public enum DamageType {

    BASIC_NORMAL("일반 공격 일반"),
    BASIC_CRIT("일반 공격 크리티컬"),
    BASIC_EXPECTED("일반 공격 기댓값"),
    SKILL_NORMAL("전투 스킬 일반"),
    SKILL_CRIT("전투 스킬 크리티컬"),
    SKILL_EXPECTED("전투 스킬 기댓값"),
    ULTIMATE_NORMAL("필살기 일반"),
    ULTIMATE_CRIT("필살기 크리티컬"),
    ULTIMATE_EXPECTED("필살기 기댓값"),
    ;

    final String krDescription;

    DamageType(String krDescription) {
        this.krDescription = krDescription;
    }
}
