package com.github.syun9274.hsr_calculator.model.entity;

import com.github.syun9274.hsr_calculator.converter.BuffListConverter;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.TraceType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table
@Entity
public class Trace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TraceType traceType;

    @Convert(converter = BuffListConverter.class)
    private List<BuffDto> buffs = new ArrayList<>();

}
