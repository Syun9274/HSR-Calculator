package com.github.syun9274.hsr_calculator.model.enums;

public enum EffectType {

    DAMAGE,
    HEAL,
    SUPPORT, // 아군 버프 목록에 기록
    CONTROL, // 군중 제어 (기절, 빙결, 둔화)
    DEBUFF,  // 적 버프 목록에 기록
    HYBRID,  // ex. 대미지를 넣으면서 버프를 제공
    ;

}
