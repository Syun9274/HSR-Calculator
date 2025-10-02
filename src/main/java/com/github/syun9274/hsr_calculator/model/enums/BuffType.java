package com.github.syun9274.hsr_calculator.model.enums;

public enum BuffType {

    // -- 스탯 버프
    HP_FLAT("체력 고정 수치 버프"),
    HP_PERCENT("체력 퍼센트 버프"),
    ATK_FLAT("공격력 고정 수치 버프"),
    ATK_PERCENT("공격력 퍼센트 버프"),
    DEF_FLAT("방어력 고정 수치 버프"),
    DEF_PERCENT("방어력 퍼센트 버프"),
    SPD_FLAT("속도 고정 수치 버프"),
    SPD_PERCENT("속도 퍼센트 버프"),
    // 아래의 네 개는 스탯의 기본 값이 백분율 형태이기에 사실 상 고정 수치 버프
    CRIT_RATE_PERCENT("치명타 확률 퍼센트 버프"),
    CRIT_DMG_PERCENT("치명타 피해 퍼센트 버프"),
    EFFECT_HIT_PERCENT("효과 명중 퍼센트 버프"),
    EFFECT_RES_PERCENT("효과 저항 퍼센트 버프"),

    // -- 원소별 피해 증가
    PHYSICAL_DAMAGE_BOOST("물리 속성 피해 증가"),
    FIRE_DAMAGE_BOOST("화염 속성 피해 증가"),
    ICE_DAMAGE_BOOST("얼음 속성 피해 증가"),
    LIGHTNING_DAMAGE_BOOST("번개 속성 피해 증가"),
    WIND_DAMAGE_BOOST("바람 속성 피해 증가"),
    QUANTUM_DAMAGE_BOOST("양자 속성 피해 증가"),
    IMAGINARY_DAMAGE_BOOST("허수 속성 피해 증가"),

    // -- 스킬 별 피해 증가
    DAMAGE_BOOST("가하는 피해 증가"),
    BASIC_ATTACK_DAMAGE_BOOST("일반 공격 피해 증가"),
    SKILL_DAMAGE_BOOST("전투 스킬 피해 증가"),
    ULTIMATE_DAMAGE_BOOST("필살기 피해 증가"),

    // -- 기타 아군 버프 효과
    DEF_IGNORE("방어 무시"),
    RES_PEN("속성 저항 관통"),

    // -- 적 버프 효과
    DAMAGE_TAKEN_INCREASE("받는 피해 증가"),
    DEF_REDUCTION("방어력 감소"),
    RES_REDUCTION("속성 저항 감소"),
    WEAKEN("약화"),
    ;

    final String krDescription;

    BuffType(String krDescription) {
        this.krDescription = krDescription;
    }

}
