package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuffDto {

    private BuffType buffType; // 버프 타입 (ex. 가하는 피해 증가)
    private double buffValue;  // 버프 수치 (소수 형태 (ex. 0.1))

}
