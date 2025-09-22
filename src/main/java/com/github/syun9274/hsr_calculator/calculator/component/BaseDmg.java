package com.github.syun9274.hsr_calculator.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class BaseDmg {

    /*
    스탯 창에 적혀있는 수치

    To find the Base DMG value, you need to use another formula:
    Base DMG = (Skill Multiplier + Extra Multiplier) * Scaling Attribute + Extra DMG

    Where:
    Skill Multiplier = this is the percentage value you can find in the skill description (Deal DMG equal to XX%),
    Extra Multiplier = this appears only on some skills, like Dan Heng's Ultimate that deals additional damage to slowed enemies,
    Scaling Attribute = this is the attribute the skill scales off - in most cases it's ATK,
    Extra DMG = this is the flat additional damage that appears on some skills.
     */


    /**
     * 공격력의 45% + 300의 피해를 가한다. 적이 둔화 상태일 경우 30%을 추가 피해를 가한다.
     *
     * @param skillMultiplier  스킬 계수 (0.45)
     * @param extraMultiplier  특정 조건 시에 추가되는 계수 (0.3)
     * @param scalingAttribute 메인 스탯 (atk)
     * @param extraDmg         추가 고정 데미지 (300)
     * @return Base DMG
     */
    public double getBaseDmg(double skillMultiplier,
                             double extraMultiplier,
                             double scalingAttribute,
                             int extraDmg) {
        return (skillMultiplier + extraMultiplier) * scalingAttribute + extraDmg;
    }
}
