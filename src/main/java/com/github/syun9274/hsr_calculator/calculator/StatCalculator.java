package com.github.syun9274.hsr_calculator.calculator;

import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Setter
@Component
public class StatCalculator {

    private double calculateFinalStat(int baseStat,
                                      List<Buff> buffs,
                                      BuffType flatType,
                                      BuffType percentType) {
        // 퍼센트 버프 합계
        double percentBuffSum = MathUtil.sumPercentBuffs(buffs, percentType);
        double afterPercentBuffSum = baseStat * (1 + percentBuffSum);

        // 고정 버프 합계
        int flatBuffSum = MathUtil.sumFlatBuffs(buffs, flatType);

        return MathUtil.toGameStatDouble(afterPercentBuffSum + flatBuffSum);
    }

    // 편의 메서드들
    public double calculateFinalHp(int baseHp, List<Buff> buffs) {
        return calculateFinalStat(baseHp, buffs, BuffType.HP_FLAT, BuffType.HP_PERCENT);
    }

    public double calculateFinalAtk(int baseAtk, List<Buff> buffs) {
        return calculateFinalStat(baseAtk, buffs, BuffType.ATK_FLAT, BuffType.ATK_PERCENT);
    }

    public double calculateFinalDef(int baseDef, List<Buff> buffs) {
        return calculateFinalStat(baseDef, buffs, BuffType.DEF_FLAT, BuffType.DEF_PERCENT);
    }

    /**
     * 캐릭터의 최종 스탯을 계산합니다. 기본 스탯과 버프 목록을 고려합니다.
     * 최종 스탯에는 HP, 공격력, 방어력이 포함됩니다.
     *
     * @param baseHp   캐릭터의 기본 HP 값
     * @param baseAtk  캐릭터의 기본 공격력 값
     * @param baseDef  캐릭터의 기본 방어력 값
     * @param buffs 캐릭터의 스탯과 관련된 버프 목록
     * @return "Hp", "Atk", "Def" 키와 계산된 값들을 포함하는 최종 스탯 Map
     */
    public Map<StatType, Double> calculateFinalStats(int baseHp, int baseAtk, int baseDef, List<Buff> buffs) {
        return Map.of(
                StatType.HP, calculateFinalHp(baseHp, buffs),
                StatType.ATK, calculateFinalAtk(baseAtk, buffs),
                StatType.DEF, calculateFinalDef(baseDef, buffs)
        );
    }

    /**
     * 조건부 스탯 증가 버프를 계산한다.
     * 예: 체력 1000당 공격력 0.8% 증가, 최대 80%
     *
     * @param baseValue     기준이 되는 스탯 값
     * @param perValue      기준 값 (예: 1000)
     * @param increaseValue 증가 값 (예: 0.8%)
     * @param maxValue      최대 증가 값 (예: 80%)
     * @return 계산된 증가 퍼센트
     */
    public static double calculateConditionalStatBonus(double baseValue,
                                                       double perValue,
                                                       double increaseValue,
                                                       double maxValue) {
        double ratio = baseValue / perValue;
        double calculatedBonus = ratio * increaseValue;

        return Math.min(calculatedBonus, maxValue);
    }
}

