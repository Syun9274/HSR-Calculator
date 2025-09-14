package com.github.syun9274.hsr_damage_calculator.calculator.formula;

public class DamageFormula {

    // 크리티컬 기본값
    public static final double DEFAULT_CRIT_RATE = 0.05; // 5%
    public static final double DEFAULT_CRIT_DMG = 1.5; // 50%

    // 방어력 한계값
    public static final double MIN_DEF = 0; // 0

    // 저항 기본값
    public static final double WEAKNESS_RESISTANCE = 0.0; // 약점 0%
    public static final double NORMAL_RESISTANCE = 0.2; // 일반 20%
    public static final double STRONG_RESISTANCE = 0.4; // 특정 원소 저항 40%

    // 저항 한계값
    public static final double MIN_RES_MULTIPLIER = 0.1; // 최소 10%
    public static final double MAX_RES_MULTIPLIER = 2.0; // 최대 200%

    // 강인성 피해 감소
    public static final double TOUGHNESS_DAMAGE_REDUCTION = 0.1; // 10%

}
