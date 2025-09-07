package com.github.syun9274.hsr_damage_calculator.calculator;

import com.github.syun9274.hsr_damage_calculator.model.Buff;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_damage_calculator.util.MathUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Setter
@Component
public class StatCalculator {

    private int calculateFinalStat(int baseStat,
                                   List<Buff> buffs,
                                   BuffType flatType,
                                   BuffType percentType) {
        // 퍼센트 버프 합계
        double percentBuffSum = buffs.stream()
                .filter(buff -> buff.getBuffType() == percentType)
                .mapToDouble(Buff::getBuffValue)
                .sum();

        double afterPercentBuffSum = baseStat * (1 + percentBuffSum);

        // 고정 버프 합계
        int flatBuffSum = buffs.stream()
                .filter(buff -> buff.getBuffType() == flatType)
                .mapToInt(buff -> (int) buff.getBuffValue())
                .sum();

        log.info("buffAtk: {}", baseStat * percentBuffSum + flatBuffSum);

        return MathUtil.toGameStatInt(afterPercentBuffSum + flatBuffSum);
    }

    // 편의 메서드들
    public int calculateFinalHp(int baseHp, List<Buff> buffs) {
        return calculateFinalStat(baseHp, buffs, BuffType.HP_FLAT, BuffType.HP_PERCENT);
    }

    public int calculateFinalAtk(int baseAtk, List<Buff> buffs) {
        return calculateFinalStat(baseAtk, buffs, BuffType.ATK_FLAT, BuffType.ATK_PERCENT);
    }

    public int calculateFinalDef(int baseDef, List<Buff> buffs) {
        return calculateFinalStat(baseDef, buffs, BuffType.DEF_FLAT, BuffType.DEF_PERCENT);
    }
}

