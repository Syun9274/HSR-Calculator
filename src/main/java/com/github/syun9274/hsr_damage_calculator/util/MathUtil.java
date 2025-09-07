package com.github.syun9274.hsr_damage_calculator.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    /**
     * 스탯 표기 방식
     * - 반올림 처리로 추정
     * @param value 스탯
     * @return 정수 변환 스탯
     */
    public static int toGameStatInt(double value) {
        return (int) Math.round(value);
    }

    /**
     * 데미지 표기 방식
     * - 올림 처리로 추정
     * @param value 데미지
     * @return 정수 변환 데미지
     */
    public static int toGameDamageInt(double value) {
        return (int) Math.ceil(value);
    }
}
