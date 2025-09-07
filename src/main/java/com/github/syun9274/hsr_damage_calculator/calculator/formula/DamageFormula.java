package com.github.syun9274.hsr_damage_calculator.calculator.formula;

public class DamageFormula {

    // 저항 기본값
    public static final double WEAKNESS_RESISTANCE = 0.0; // 약점 0%
    public static final double NORMAL_RESISTANCE = 0.2; // 일반 20%
    public static final double STRONG_RESISTANCE = 0.4; // 정예 40%

    // 저항 한계값
    public static final double MIN_RESISTANCE = -1.0; // -100%
    public static final double MAX_RESISTANCE = 0.9; // 90%

    // 강인성 피해 감소
    public static final double TOUGHNESS_DAMAGE_REDUCTION = 0.1; // 10%

}
