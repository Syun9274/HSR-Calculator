package com.github.syun9274.hsr_calculator.model.entity;

import com.github.syun9274.hsr_calculator.converter.BuffListConverter;
import com.github.syun9274.hsr_calculator.dto.Buff;
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
public class Skill extends BaseAbilityEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EffectType effectType;

    // 공격 타입일 경우
    private Double skillMultiplier;

    private Double extraMultiplier;

    private Integer extraDamage;

    // 버프 타입일 경우
    @Convert(converter = BuffListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Buff> buffs = new ArrayList<>();

    // 강화된 정보
    private Double variantSkillMultiplier;

    private Double variantExtraMultiplier;

    private Integer variantExtraDamage;

    // 강화된 버프 정보
    @Convert(converter = BuffListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Buff> variantBuffs = new ArrayList<>();

}
