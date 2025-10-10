package com.github.syun9274.hsr_calculator.model.entity;

import com.github.syun9274.hsr_calculator.converter.BuffListConverter;
import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.DamageInfo;
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
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "skillMultiplier", column = @Column(name = "skill_multiplier", nullable = false)),
            @AttributeOverride(name = "extraMultiplier", column = @Column(name = "extra_multiplier")),
            @AttributeOverride(name = "extraDamage", column = @Column(name = "extra_damage"))
    })
    private DamageInfo damageInfo;

    // 버프 타입일 경우
    @Convert(converter = BuffListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Buff> buffs = new ArrayList<>();

}
