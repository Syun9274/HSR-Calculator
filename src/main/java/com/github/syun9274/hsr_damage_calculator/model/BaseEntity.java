package com.github.syun9274.hsr_damage_calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    private Long id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int baseHp;

    @Column(nullable = false)
    private int baseAtk;

    @Column(nullable = false)
    private int baseDef;

}
