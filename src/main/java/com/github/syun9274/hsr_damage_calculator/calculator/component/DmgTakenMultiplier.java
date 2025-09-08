package com.github.syun9274.hsr_damage_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class DmgTakenMultiplier {

    /*
    받는 피해 증가 요소 (ex. 삼포 궁극기 지딜피해 증가)

    This portion of the equation functions differently from Genshin Impact,
    where it would normally be a part of DMG%. In Honkai: Star Rail,
    the DMG Taken Multiplier operates by the following equation:
    DMG Taken Multiplier = 100% + Elemental DMG Taken% + All Type DMG Taken%

    Example sources that affect this multiplier include Welt’s Ultimate as well as Sampo’s Ultimate.
     */
}
