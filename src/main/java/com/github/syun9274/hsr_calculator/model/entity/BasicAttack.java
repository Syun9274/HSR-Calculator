package com.github.syun9274.hsr_calculator.model.entity;

import com.github.syun9274.hsr_calculator.dto.DamageInfo;
import com.github.syun9274.hsr_calculator.model.base.BaseAbilityEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class BasicAttack extends BaseAbilityEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "skillMultiplier", column = @Column(name = "skill_multiplier", nullable = false)),
            @AttributeOverride(name = "extraMultiplier", column = @Column(name = "extra_multiplier")),
            @AttributeOverride(name = "extraDamage", column = @Column(name = "extra_damage"))
    })
    private DamageInfo damageInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "skillMultiplier", column = @Column(name = "variant_skill_multiplier")),
            @AttributeOverride(name = "extraMultiplier", column = @Column(name = "variant_extra_multiplier")),
            @AttributeOverride(name = "extraDamage", column = @Column(name = "variant_extra_damage"))
    })
    private DamageInfo variantDamageInfo;

}
