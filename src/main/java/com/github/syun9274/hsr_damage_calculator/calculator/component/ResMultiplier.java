package com.github.syun9274.hsr_damage_calculator.calculator.component;

import com.github.syun9274.hsr_damage_calculator.calculator.formula.DamageFormula;
import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.Character;
import com.github.syun9274.hsr_damage_calculator.model.Enemy;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_damage_calculator.model.enums.Element;
import com.github.syun9274.hsr_damage_calculator.util.MathUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResMultiplier {

    /*
    RES = 원소 저항
    RES PEN = 속성 저항 관통

    The RES Multiplier operates by the following equation:
    RES Multiplier = 100% - (RES% - RES PEN%)

    All enemies appear to have a base 20% RES to all elements unless they have innate weaknesses or resistances.
    If an enemy is weak to an element, then they have 0% RES to that element.
    If an enemy is resistant to an element, then they have 40% RES to that element.

    RES cannot go below -100% or above 90%.
     */

    /**
     * 원소 저항 배수 계산
     * RES Multiplier = 1 - (적 저항% - 저항 관통%)
     *
     * @param character 공격하는 캐릭터 (원소 속성 확인용)
     * @param enemy     방어하는 적 (약점/저항 속성 확인용)
     * @param buffs     캐릭터의 버프 목록 (속성 저항 관통 효과)
     * @return 저항으로 인한 데미지 배수 (0.1 ~ 1.9 범위로 제한)
     */
    public double getResMultiplier(Character character, Enemy enemy, List<Buff> buffs) {
        double resPercent = calculateResPercent(
                character.getElement(),
                enemy.getWeaknessElements(),    // List<Element>
                enemy.getResistElements());     // List<Element>
        double resPen = calculateResPen(buffs);
        double res = 1 - (resPercent - resPen);

        // Math.clamp(value, min, max)는 값을 min과 max 사이로 제한해주는 메서드
        return Math.clamp(res, DamageFormula.MIN_RESISTANCE, DamageFormula.MAX_RESISTANCE);
    }

    /**
     * 캐릭터 원소에 따른 적의 원소 저항 값을 계산합니다
     * -> 약점 0%, 일반 20%, 저항 40%
     *
     * @param charElement        캐릭터 원소
     * @param weaknessElements   적 약점 원소
     * @param resistanceElements 적 저항 원소
     * @return 원소 저항 값
     */
    private double calculateResPercent(Element charElement,
                                       List<Element> weaknessElements,
                                       List<Element> resistanceElements) {
        if (weaknessElements.contains(charElement)) {
            return DamageFormula.WEAKNESS_RESISTANCE;  // 0%
        } else if (resistanceElements.contains(charElement)) {
            return DamageFormula.STRONG_RESISTANCE;    // 40%
        } else {
            return DamageFormula.NORMAL_RESISTANCE;    // 20%
        }
    }

    /**
     * 속성 저항 관통
     *
     * @param buffs 적용 중인 buff list
     * @return 속성 저항 관통 수치 합연산
     */
    private double calculateResPen(List<Buff> buffs) {
        return MathUtil.sumPercentBuffs(
                buffs,
                BuffType.RES_PEN);
    }
}