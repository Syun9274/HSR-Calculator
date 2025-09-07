package com.github.syun9274.hsr_damage_calculator.model;

import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Buff {

    private BuffType buffType;
    private double buffValue; // 소수 형태 (ex. 0.1)

}
