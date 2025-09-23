package com.github.syun9274.hsr_calculator.model;

import com.github.syun9274.hsr_calculator.model.base.BaseEntity;
import com.github.syun9274.hsr_calculator.model.character.BasicAttack;
import com.github.syun9274.hsr_calculator.model.character.Skill;
import com.github.syun9274.hsr_calculator.model.character.Ultimate;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import com.github.syun9274.hsr_calculator.model.enums.FatePath;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    // 메인 스탯 (HP || ATK || DEF)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private StatType scalingAttribute;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private BasicAttack basicAttack;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private Skill skill;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private Ultimate ultimate;

}
