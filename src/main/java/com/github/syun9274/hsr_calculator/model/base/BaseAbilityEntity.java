package com.github.syun9274.hsr_calculator.model.base;

import com.github.syun9274.hsr_calculator.model.Character;
import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseAbilityEntity {

    @Id
    private Long characterId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "character_id")
    private Character character;

}
