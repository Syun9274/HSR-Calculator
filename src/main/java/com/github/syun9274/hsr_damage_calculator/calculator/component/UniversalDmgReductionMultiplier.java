package com.github.syun9274.hsr_damage_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class UniversalDmgReductionMultiplier {

    /*
    This portion of the equation is as follows:
    Universal DMG Reduction Multiplier = 100% * (1 - DMG Reduction_1) * (1 - DMG Reduction_2) * ...

    When an enemy has Toughness, they have 10% Universal DMG Reduction,
    which is reduced to 0% when broken.
    Note this multiplier stacks multiplicative with other sources.
     */



}
