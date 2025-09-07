package com.github.syun9274.hsr_damage_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class DmgMultiplier {

    /*
    All DMG% is added together and grouped into one place in the DMG equation.

    The equation is as follows:
    DMG% Multiplier = 100% + Elemental DMG% + All Type DMG% + DoT DMG% + Other DMG%

    Remove DMG% multipliers if they are not relevant to the calculation.
    For example, if Hook has 38.9% Fire DMG and gains 20% DMG against
    a Burning/Bleeding enemy from “Woof! Walk Time!” then
    her total DMG% multiplier is 158.9% against a Burning or Bleeding enemy,
    but only 138.9% against a non-Burning or Bleeding enemy.
     */
}
