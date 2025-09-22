package com.github.syun9274.hsr_calculator.model.enums;

import lombok.Getter;

@Getter
public enum StatType {

    HP("체력"),
    ATK("공격력"),
    DEF("방어력"),
    SPD("속도"),
    CRIT_RATE("치명타 확률"),
    CRIT_DMG("치명타 피해"),
    EFFECT_HIT("효과 명중"),
    EFFECT_RES("효과 저항"),
    ;

    final String krDescription;

    StatType(String krDescription) {
        this.krDescription = krDescription;
    }
}
