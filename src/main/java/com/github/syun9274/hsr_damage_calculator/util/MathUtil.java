package com.github.syun9274.hsr_damage_calculator.util;

import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class MathUtil {

    /**
     * 스탯 표기 방식
     * - 소수점 처리 기준을 모르겠음
     * @param value 스탯
     * @return 정수 변환 스탯
     */
    public static int toGameStatInt(double value) {
        return (int) Math.round(value);
    }

    /**
     * 데미지 표기 방식
     * - 소수점 처리 기준을 모르겠음
     * @param value 데미지
     * @return 정수 변환 데미지
     */
    public static int toGameDamageInt(double value) {
        return (int) Math.ceil(value);
    }

    /**
     * 백분율을 소수로 변환
     * @param percentage 백분율 (예: 75.5)
     * @return 소수 (예: 0.755)
     */
    public static double percentToDecimal(double percentage) {
        return percentage / 100.0;
    }

    /**
     * 여러 타입의 고정 버프 합계 계산
     */
    public static int sumFlatBuffs(List<Buff> buffs, BuffType... buffTypes) {
        return buffs.stream()
                .filter(buff -> Arrays.stream(buffTypes).anyMatch(type -> buff.getBuffType() == type))
                .mapToInt(buff -> (int) buff.getBuffValue())
                .sum();
    }

    /**
     * 여러 타입의 퍼센트 버프 합계 계산
     */
    public static double sumPercentBuffs(List<Buff> buffs, BuffType... buffTypes) {
        return buffs.stream()
                .filter(buff -> Arrays.stream(buffTypes).anyMatch(type -> buff.getBuffType() == type))
                .mapToDouble(Buff::getBuffValue)
                .sum();
    }
}
