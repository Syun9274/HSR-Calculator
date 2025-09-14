package com.github.syun9274.hsr_damage_calculator.model;

import com.github.syun9274.hsr_damage_calculator.model.enums.Element;
import com.github.syun9274.hsr_damage_calculator.model.enums.FatePath;
import com.github.syun9274.hsr_damage_calculator.model.enums.AbilityType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class Character extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Element element;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private FatePath fatePath;

    // 메인 스탯 (hp || atk || def)
    @Column(nullable = false, updatable = false)
    private String scalingAttribute;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<CharacterAbility> abilities;

    // 편의 메서드들
    public CharacterAbility getBasicAttack() {
        return abilities.stream()
                .filter(skill -> skill.getAbilityType() == AbilityType.BASIC)
                .findFirst().orElse(null);
    }

    public CharacterAbility getSkill() {
        return abilities.stream()
                .filter(skill -> skill.getAbilityType() == AbilityType.SKILL)
                .findFirst().orElse(null);
    }

    public CharacterAbility getUltimate() {
        return abilities.stream()
                .filter(skill -> skill.getAbilityType() == AbilityType.ULTIMATE)
                .findFirst().orElse(null);
    }

    public List<CharacterAbility> getTraces() {
        return abilities.stream()
                .filter(ability -> ability.getAbilityType() == AbilityType.TRACE)
                .toList();
    }

}
