package com.github.syun9274.hsr_damage_calculator.model.enums;

public enum BuffType {

    // 스탯 버프
    HP_FLAT("체력 고정 수치 버프"),
    HP_PERCENT("체력 퍼센트 버프"),
    ATK_FLAT("공격력 고정 수치 버프"),
    ATK_PERCENT("공격력 퍼센트 버프"),
    DEF_FLAT("방어력 고정 수치 버프"),
    DEF_PERCENT("방어력 퍼센트 버프"),

    // 가피증 버프
    DAMAGE_BOOST("가하는 피해 증가"),
    BASIC_ATTACK_DAMAGE_BOOST("일반 공격 피해 증가"),
    SKILL_DAMAGE_BOOST("전투 스킬 피해 증가"),
    ULTIMATE_DAMAGE_BOOST("필살기 피해 증가"),

    // 받피증 버프
    DAMAGE_TAKEN_INCREASE("받는 피해 증가"),
    DEF_IGNORE("방어 무시"),
    RES_PEN("속성 저항 관통"),
    WEAKEN("약화"),
    ;

    final String krDescription;

    BuffType(String krDescription) {
        this.krDescription = krDescription;
    }
}
