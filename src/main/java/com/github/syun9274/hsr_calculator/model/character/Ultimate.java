package com.github.syun9274.hsr_calculator.model.character;

import com.github.syun9274.hsr_calculator.converter.BuffListConverter;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.base.BaseAbilityEntity;
import com.github.syun9274.hsr_calculator.model.enums.EffectType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class Ultimate extends BaseAbilityEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EffectType effectType;

    // 공격 타입일 경우
    private double skillMultiplier;

    private double extraMultiplier;

    private int extraDamage;

    // ---
    // 버프 타입일 경우
    @Convert(converter = BuffListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<BuffDto> buffs = new ArrayList<>();


}
