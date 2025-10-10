package com.github.syun9274.hsr_calculator.dto.character;

import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.model.enums.TraceType;

import java.util.List;

public record TraceDto(
        TraceType traceType,
        List<Buff> buffs
) {

}
