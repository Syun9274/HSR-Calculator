package com.github.syun9274.hsr_calculator.calculator.component;

import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
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

    /**
     * 피해 증가 배수 계산 (공통 로직)
     * <p>
     * DMG Multiplier = 1 + (속성 피해 증가% + 전체 피해 증가% + 특정 스킬 피해 증가% + ...)
     *
     * @param buffDtos              아군이 받는 버프 (가하는 피해 증가)
     * @param specificDamageType 특정 스킬 타입의 피해 증가 버프
     * @return 피해 증가 배수 (1.0 = 100%, 1.5 = 150%)
     */
    private double getDmgMultiplier(List<BuffDto> buffDtos, BuffType specificDamageType) {
        return 1 + MathUtil.sumPercentBuffs(buffDtos, BuffType.DAMAGE_BOOST, specificDamageType);
    }

    /**
     * 피해 증가 buff 합연산 (일반 공격)
     *
     * @param buffDtos 적용 중인 buff list
     * @return 피해 증가 수치 합연산
     */
    public double getBasicAttackDmgMultiplier(List<BuffDto> buffDtos) {
        return getDmgMultiplier(buffDtos, BuffType.BASIC_ATTACK_DAMAGE_BOOST);
    }

    /**
     * 피해 증가 buff 합연산 (전투 스킬)
     *
     * @param buffDtos 적용 중인 buff list
     * @return 피해 증가 수치 합연산
     */
    public double getSkillDmgMultiplier(List<BuffDto> buffDtos) {
        return getDmgMultiplier(buffDtos, BuffType.SKILL_DAMAGE_BOOST);
    }

    /**
     * 피해 증가 buff 합연산 (필살기)
     *
     * @param buffDtos 적용 중인 buff list
     * @return 피해 증가 수치 합연산
     */
    public double getUltimateDmgMultiplier(List<BuffDto> buffDtos) {
        return getDmgMultiplier(buffDtos, BuffType.ULTIMATE_DAMAGE_BOOST);
    }
}
