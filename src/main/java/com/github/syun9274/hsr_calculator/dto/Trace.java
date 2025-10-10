package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.model.enums.TraceType;

import java.util.List;

public record Trace(
        TraceType traceType,
        DamageInfo damageInfo,
        List<Buff> buffs
) {
    public Trace(TraceType traceType, DamageInfo damageInfo) {
        this(traceType, damageInfo, null);
    }

    public Trace(TraceType traceType, List<Buff> buffs) {
        this(traceType, null, buffs);
    }
}
