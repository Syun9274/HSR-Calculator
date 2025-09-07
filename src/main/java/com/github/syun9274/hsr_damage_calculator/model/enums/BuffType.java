package com.github.syun9274.hsr_damage_calculator.model.enums;

public enum BuffType {

    HP_FLAT("체력 고정 수치 버프"),
    HP_PERCENT("체력 퍼센트 버프"),
    ATK_FLAT("공격력 고정 수치 버프"),
    ATK_PERCENT("공격력 퍼센트 버프"),
    DEF_FLAT("방어력 고정 수치 버프"),
    DEF_PERCENT("방어력 퍼센트 버프");

    final String krDescription;

    BuffType(String krDescription) {
        this.krDescription = krDescription;
    }
}
