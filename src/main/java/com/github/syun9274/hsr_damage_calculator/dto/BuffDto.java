package com.github.syun9274.hsr_damage_calculator.dto;

import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuffDto {

    private BuffType buffType;

    private double buffValue; // 소수 형태 (ex. 0.1)

}
