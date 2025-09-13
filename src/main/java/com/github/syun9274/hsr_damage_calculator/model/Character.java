package com.github.syun9274.hsr_damage_calculator.model;

import com.github.syun9274.hsr_damage_calculator.model.enums.Element;
import com.github.syun9274.hsr_damage_calculator.model.enums.SkillType;
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

    // 메인 스탯 (hp || atk || def)
    @Column(nullable = false, updatable = false)
    private String scalingAttribute;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<CharacterSkill> skills;

    // 편의 메서드들
    public CharacterSkill getBasicAttack() {
        return skills.stream()
                .filter(skill -> skill.getSkillType() == SkillType.BASIC)
                .findFirst().orElse(null);
    }

    public CharacterSkill getSkill() {
        return skills.stream()
                .filter(skill -> skill.getSkillType() == SkillType.SKILL)
                .findFirst().orElse(null);
    }

    public CharacterSkill getUltimate() {
        return skills.stream()
                .filter(skill -> skill.getSkillType() == SkillType.ULTIMATE)
                .findFirst().orElse(null);
    }

}
