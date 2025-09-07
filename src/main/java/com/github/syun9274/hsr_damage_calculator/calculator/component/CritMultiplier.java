package com.github.syun9274.hsr_damage_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class CritMultiplier {

    /*
    Crit Multiplier = 1 + (Crit Rate × Crit DMG)

    결과를 보여줄 때
    1. 치명타 o
    2. 치명타 x
    3. 평균 기대값 데미지
     */

    double defaultCritRate = 0.05; // 5%
    double defaultCritDamage = 0.5; // 50%
}
