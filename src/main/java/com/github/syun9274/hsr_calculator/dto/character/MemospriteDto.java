package com.github.syun9274.hsr_calculator.dto.character;

import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.model.enums.StatType;

import java.util.List;

public record MemospriteDto(
        StatType mainStatType,   // 메인 스탯 (기억 정렁 스탯의 HP, ATK, DEF)
        double mainStatValue,    // 메인 스탯의 값 (테이블에 있는 Ratio 계산 후의 값)
        double spriteMultiplier,
        List<Buff> spriteBuffs
) {

}
