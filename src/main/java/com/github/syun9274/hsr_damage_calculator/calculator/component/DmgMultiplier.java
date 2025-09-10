package com.github.syun9274.hsr_damage_calculator.calculator.component;

import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_damage_calculator.util.MathUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DmgMultiplier {

    /*
    데미지 증가 요소 (피해 증가 구체, 광추 옵션 등)

    All DMG% is added together and grouped into one place in the DMG equation.

    The equation is as follows:
    DMG% Multiplier = 100% + Elemental DMG% + All Type DMG% + DoT DMG% + Other DMG%

    Remove DMG% multipliers if they are not relevant to the calculation.
    For example, if Hook has 38.9% Fire DMG and gains 20% DMG against
    a Burning/Bleeding enemy from “Woof! Walk Time!” then
    her total DMG% multiplier is 158.9% against a Burning or Bleeding enemy,
    but only 138.9% against a non-Burning or Bleeding enemy.
     */

    private double getDmgMultiplier(List<Buff> buffs, BuffType specificDamageType) {
        return 1 + MathUtil.sumPercentBuffs(buffs, BuffType.DAMAGE_BOOST, specificDamageType);
    }

    /**
     * 피해 증가 buff 합연산 (일반 공격)
     *
     * @param buffs 적용 중인 buff list
     * @return 피해 증가 수치 합연산
     */
    public double getBasicAttackDmgMultiplier(List<Buff> buffs) {
        return getDmgMultiplier(buffs, BuffType.BASIC_ATTACK_DAMAGE_BOOST);
    }

    /**
     * 피해 증가 buff 합연산 (전투 스킬)
     *
     * @param buffs 적용 중인 buff list
     * @return 피해 증가 수치 합연산
     */
    public double getSkillDmgMultiplier(List<Buff> buffs) {
        return getDmgMultiplier(buffs, BuffType.SKILL_DAMAGE_BOOST);
    }

    /**
     * 피해 증가 buff 합연산 (필살기)
     *
     * @param buffs 적용 중인 buff list
     * @return 피해 증가 수치 합연산
     */
    public double getUltimateDmgMultiplier(List<Buff> buffs) {
        return getDmgMultiplier(buffs, BuffType.ULTIMATE_DAMAGE_BOOST);
    }
}
