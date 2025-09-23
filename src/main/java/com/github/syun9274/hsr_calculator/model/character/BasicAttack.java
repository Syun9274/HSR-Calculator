package com.github.syun9274.hsr_calculator.model.character;

import com.github.syun9274.hsr_calculator.model.base.BaseAbilityEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class BasicAttack extends BaseAbilityEntity {

    @Column(nullable = false)
    private double skillMultiplier;

    private double extraMultiplier;

    private int extraDamage;

}
