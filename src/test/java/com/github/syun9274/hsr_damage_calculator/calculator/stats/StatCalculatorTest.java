package com.github.syun9274.hsr_damage_calculator.calculator.stats;

import com.github.syun9274.hsr_damage_calculator.calculator.StatCalculator;
import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.github.syun9274.hsr_damage_calculator.util.MathUtil.percentToDecimal;
import static org.junit.jupiter.api.Assertions.*;

class StatCalculatorTest {

    private StatCalculator statCalculator;

    @BeforeEach
    void setUp() {
        statCalculator = new StatCalculator();
    }

    @Test
    @DisplayName("버프 없이 기본 공격력 계산")
    void calculateFinalAtk_NoBuff() {
        // given
        int baseAtk = 1236;
        List<Buff> buffs = Collections.emptyList();

        // when
        double result = statCalculator.calculateFinalAtk(baseAtk, buffs);

        // then
        assertEquals(1236, result);
    }

    @Test
    @DisplayName("공격력 고정 버프 적용 - 제레")
    void calculateFinalAtk_OnlyFlatBuffs() {
        // given
        int baseAtk = 1222;
        List<Buff> buffs = Arrays.asList(
                new Buff(BuffType.ATK_FLAT, 56), // 장갑 0강화
                // 기본 행적 버프 (제거 불가)
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(4)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(4)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(6)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(6)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(8))
        );

        // when
        double result = statCalculator.calculateFinalAtk(baseAtk, buffs);

        // then
        assertTrue(result >= 1618 && result <= 1622,
                "Expected result between 1618 - 1622, but was: " + result);
        // 인게임 표시: 1222 + 398 -> 1621
    }

    @Test
    @DisplayName("공격력 버프 모두 적용 - 제레")
    void calculateFinalAtk_MixedBuffs() {
        // given
        int baseAtk = 1222;
        List<Buff> buffs = Arrays.asList(
                new Buff(BuffType.ATK_FLAT, 352),
                new Buff(BuffType.ATK_FLAT, 19),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(8.6)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(43.2)),
                // 기본 행적 버프 (제거 불가)
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(4)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(4)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(6)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(6)),
                new Buff(BuffType.ATK_PERCENT, percentToDecimal(8))
        );

        // when
        double result = statCalculator.calculateFinalAtk(baseAtk, buffs);

        // then
        assertTrue(result >= 2568 && result <= 2572,
                "Expected result between 2568 - 2572, but was: " + result);
        // 인게임 표시: 1222 + 1347 -> 2570
        // 테스트 결과 1222 + 1346.156 -> 2568.2
    }

    @Test
    @DisplayName("관련 없는 버프는 무시")
    void calculateFinalAtk_IgnoreOtherBuffTypes() {
        // given
        int baseAtk = 1000;
        List<Buff> buffs = Arrays.asList(
                new Buff(BuffType.ATK_FLAT, 200),
                new Buff(BuffType.HP_FLAT, 500),      // 무시되어야 함
                new Buff(BuffType.DEF_PERCENT, 0.2)   // 무시되어야 함
        );

        // when
        double result = statCalculator.calculateFinalAtk(baseAtk, buffs);

        // then
        assertEquals(1200, result);
    }

}