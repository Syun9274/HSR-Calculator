package com.github.syun9274.hsr_calculator.model.entity;

import com.github.syun9274.hsr_calculator.converter.BuffListConverter;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.base.BaseAbilityEntity;
import com.github.syun9274.hsr_calculator.model.enums.CalculationType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class Memosprite extends BaseAbilityEntity {

    // 기억정령 스탯 계산
    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;

    // ATK, HP, DEF (어떤 스탯 기준인지)
    @Enumerated(EnumType.STRING)
    private StatType baseStatType;

    // 캐릭터 스탯의 몇 %인지 (예: 0.4 = 40%)
    private double statRatio;

    private Integer fixedStatValue;

    // 공격 계수
    private double spriteMultiplier;

    @Convert(converter = BuffListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<BuffDto> spriteBuffs = new ArrayList<>();

}
