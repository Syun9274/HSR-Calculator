package com.github.syun9274.hsr_damage_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class ResMultiplier {

    /*
    The RES Multiplier operates by the following equation:
    RES Multiplier = 100% - (RES% - RES PEN%)

    All enemies appear to have a base 20% RES to all elements unless they have innate weaknesses or resistances.
    If an enemy is weak to an element, then they have 0% RES to that element.
    If an enemy is resistant to an element, then they have 40% RES to that element.

    RES cannot go below -100% or above 90%.
     */
}