package com.github.syun9274.hsr_calculator.calculator;

import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Setter
@Component
public class StatCalculator {

    private double calculateFinalStat(int baseStat,
                                      List<BuffDto> buffDtos,
                                      BuffType flatType,
                                      BuffType percentType) {
        // 퍼센트 버프 합계
        double percentBuffSum = MathUtil.sumPercentBuffs(buffDtos, percentType);
        double afterPercentBuffSum = baseStat * (1 + percentBuffSum);

        // 고정 버프 합계
        int flatBuffSum = MathUtil.sumFlatBuffs(buffDtos, flatType);

        return MathUtil.toGameStatDouble(afterPercentBuffSum + flatBuffSum);
    }

    // 편의 메서드들
    public double calculateFinalHp(int baseHp, List<BuffDto> buffDtos) {
        return calculateFinalStat(baseHp, buffDtos, BuffType.HP_FLAT, BuffType.HP_PERCENT);
    }

    public double calculateFinalAtk(int baseAtk, List<BuffDto> buffDtos) {
        return calculateFinalStat(baseAtk, buffDtos, BuffType.ATK_FLAT, BuffType.ATK_PERCENT);
    }

    public double calculateFinalDef(int baseDef, List<BuffDto> buffDtos) {
        return calculateFinalStat(baseDef, buffDtos, BuffType.DEF_FLAT, BuffType.DEF_PERCENT);
    }

    /**
     * 캐릭터의 최종 스탯을 계산합니다. 기본 스탯과 버프 목록을 고려합니다.
     * 최종 스탯에는 HP, 공격력, 방어력이 포함됩니다.
     *
     * @param baseHp   캐릭터의 기본 HP 값
     * @param baseAtk  캐릭터의 기본 공격력 값
     * @param baseDef  캐릭터의 기본 방어력 값
     * @param buffDtos 캐릭터의 스탯과 관련된 버프 목록
     * @return "Hp", "Atk", "Def" 키와 계산된 값들을 포함하는 최종 스탯 Map
     */
    public Map<String, Double> calculateFinalStats(int baseHp, int baseAtk, int baseDef, List<BuffDto> buffDtos) {
        return Map.of(
                "Hp", calculateFinalHp(baseHp, buffDtos),
                "Atk", calculateFinalAtk(baseAtk, buffDtos),
                "Def", calculateFinalDef(baseDef, buffDtos)
        );
    }
}

