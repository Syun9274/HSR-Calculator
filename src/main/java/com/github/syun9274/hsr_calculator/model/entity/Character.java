package com.github.syun9274.hsr_calculator.model.entity;

import com.github.syun9274.hsr_calculator.converter.TraceListConverter;
import com.github.syun9274.hsr_calculator.dto.Trace;
import com.github.syun9274.hsr_calculator.model.base.BaseEntity;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import com.github.syun9274.hsr_calculator.model.enums.FatePath;
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
public class Character extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Element element;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private FatePath fatePath;

    // 메인 스탯 (HP || ATK || DEF)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatType scalingAttribute;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private BasicAttack basicAttack;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private Skill skill;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private Ultimate ultimate;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private Talent talent;

    @Convert(converter = TraceListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Trace> traces = new ArrayList<>();

    // 운명의 길이 `기억`인 캐릭터들만 해당
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    private Memosprite memosprite;

}
