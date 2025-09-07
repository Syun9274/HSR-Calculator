package com.github.syun9274.hsr_damage_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class BaseDmg {

    /*
    To find the Base DMG value, you need to use another formula:
    Base DMG = (Skill Multiplier + Extra Multiplier) * Scaling Attribute + Extra DMG

    Where:
    Skill Multiplier = this is the percentage value you can find in the skill description (Deal DMG equal to XX%),
    Extra Multiplier = this appears only on some skills, like Dan Heng's Ultimate that deals additional damage to slowed enemies,
    Scaling Attribute = this is the attribute the skill scales off - in most cases it's ATK,
    Extra DMG = this is the flat additional damage that appears on some skills.
     */

    double getBaseDmg(int hp, int atk, int def,
                      double hpDamageRate,
                      double atkDamageRate,
                      double defDamageRate) {
        return hpDamageRate * hp + atkDamageRate * atk + defDamageRate * def;
    }
}
