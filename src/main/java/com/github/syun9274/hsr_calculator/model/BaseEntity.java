package com.github.syun9274.hsr_calculator.model;

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

    private int level;

    private int baseHp;

    private int baseAtk;

    private int baseDef;

}
