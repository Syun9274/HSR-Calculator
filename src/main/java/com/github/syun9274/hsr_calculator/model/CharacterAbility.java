package com.github.syun9274.hsr_calculator.model;

import com.github.syun9274.hsr_calculator.model.enums.AbilityType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class CharacterAbility {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    @Enumerated(EnumType.STRING)
    private AbilityType abilityType; // BASIC, SKILL, ULTIMATE, TRACE

    // 스킬 계수 정보
    private double skillMultiplier;

    // 조건부 추가 계수
    private double extraMultiplier;

    private int extraDamage;
}
