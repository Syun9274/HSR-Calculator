package com.github.syun9274.hsr_damage_calculator.calculator.component;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefMultiplier {

    /*
    방어력 보정 요소 (적 방어력)

    DEF Multiplier scales with the level difference between characters, similar to Genshin Impact.
    The higher the attacker level compared to defender level, the greater the DEF Multiplier.

    The DEF-related equations for a normal defender are:
    DEF Multiplier = 100% - [DEF / (DEF + 200 + 10 * Attacker Level)]

    To find the DEF, you need to use another formula:
    DEF = Base DEF * (100% + DEF% - (DEF Reduction + DEF Ignore)) + Flat DEF

    DEF cannot go below 0.
     */
}
