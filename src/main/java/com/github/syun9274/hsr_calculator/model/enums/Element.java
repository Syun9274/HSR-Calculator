package com.github.syun9274.hsr_calculator.model.enums;

import lombok.Getter;

@Getter
public enum Element {

    PHYSICAL("물리", 2),
    FIRE("화염", 2, 1),
    ICE("얼음", 0.5, 1),
    LIGHTNING("번개", 1, 2),
    WIND("바람", 1.5, 1),
    QUANTUM("양자", 0.5, 1.5),
    IMAGINARY("허수", 0.5);

    final String krDescription;
    final double brokenRate; // 격파 계수
    double damageRate; // 지속 피해 계수

    /**
     * 지속 피해 고정 계수가 없는 속성
     * -> 물리, 허수
     *
     * @param krDescription 한국어 번역
     * @param brokenRate    격파 계수
     */
    Element(String krDescription, double brokenRate) {
        this.krDescription = krDescription;
        this.brokenRate = brokenRate;
    }

    /**
     * 지속 피해 고정 계수가 있는 속성
     * -> 화염, 얼음, 번개, 바람, 양자
     *
     * @param krDescription 한국어 번역
     * @param brokenRate    격파 계수
     * @param damageRate    지속 피해 계수
     */
    Element(String krDescription, double brokenRate, double damageRate) {
        this.krDescription = krDescription;
        this.brokenRate = brokenRate;
        this.damageRate = damageRate;
    }

}
