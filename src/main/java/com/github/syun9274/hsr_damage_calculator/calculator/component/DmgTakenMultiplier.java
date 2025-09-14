package com.github.syun9274.hsr_damage_calculator.calculator.component;

import com.github.syun9274.hsr_damage_calculator.dto.BuffDto;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_damage_calculator.util.MathUtil;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 받는 피해 증가 배수 계산
     * <p>
     * DMG Taken Multiplier = 1 + (속성별 받는 피해 증가% + 전체 받는 피해 증가%)
     *
     * @param buffDtos 적에게 적용된 디버프 목록 (받는 피해 증가 효과)
     * @return 받는 피해 증가 배수 (1.0 = 100%, 1.25 = 125%)
     */
    public double getDmgTakenMultiplier(List<BuffDto> buffDtos) {
        return 1 + calculateElementalDmgTakenMultiplier(buffDtos);
    }

    private double calculateElementalDmgTakenMultiplier(List<BuffDto> buffDtos) {
        return MathUtil.sumPercentBuffs(buffDtos, BuffType.DAMAGE_TAKEN_INCREASE);
    }
}
