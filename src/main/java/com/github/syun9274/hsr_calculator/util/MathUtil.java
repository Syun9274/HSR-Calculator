package com.github.syun9274.hsr_calculator.util;

import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class MathUtil {

    /**
     * 스탯 표기 방식
     * - 소수점 한 자리까지 표현
     *
     * @param value 스탯
     * @return 소수점 한자리까지 반올림된 스탯
     */
    public static double toGameStatDouble(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    /**
     * 데미지 표기 방식
     * - 우선은 반올림 처리
     *
     * @param value 데미지
     * @return 정수 변환 데미지
     */
    public static int toGameDamageInt(double value) {
        return (int) Math.round(value);
    }

    /**
     * 백분율을 소수로 변환
     *
     * @param percentage 백분율 (예: 75.5)
     * @return 소수 (예: 0.755)
     */
    public static double percentToDecimal(double percentage) {
        return percentage / 100.0;
    }

    /**
     * 지정된 버프 타입들로 필터링된 버프 목록에서 고정 버프 값 합계 계산
     *
     * @param buffDtos     버프 타입과 값을 포함하는 버프 객체 목록
     * @param buffTypes 버프를 필터링할 버프 타입 배열
     * @return 지정된 버프 타입들에 대한 고정 버프 값들의 총합 (정수)
     */
    public static int sumFlatBuffs(List<BuffDto> buffDtos, BuffType... buffTypes) {
        return buffDtos.stream()
                .filter(buff -> Arrays.stream(buffTypes).anyMatch(type -> buff.getBuffType() == type))
                .mapToInt(buff -> (int) buff.getBuffValue())
                .sum();
    }

    /**
     * 지정된 버프 타입들로 필터링된 버프 목록에서 퍼센트 버프 값 합계 계산
     *
     * @param buffDtos     버프 타입과 값을 포함하는 버프 객체 목록
     * @param buffTypes 버프를 필터링할 버프 타입 배열
     * @return 지정된 버프 타입들에 대한 퍼센트 버프 값들의 총합
     */
    public static double sumPercentBuffs(List<BuffDto> buffDtos, BuffType... buffTypes) {
        return buffDtos.stream()
                .filter(buff -> Arrays.stream(buffTypes).anyMatch(type -> buff.getBuffType() == type))
                .mapToDouble(BuffDto::getBuffValue)
                .sum();
    }
}
