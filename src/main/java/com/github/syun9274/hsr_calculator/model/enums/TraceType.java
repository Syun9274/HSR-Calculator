package com.github.syun9274.hsr_calculator.model.enums;

public enum TraceType {

    MAJOR_PASSIVE("추가 능력"),
    MINOR_NODE("노드"),
    ;

    final String krDescription;

    TraceType(final String krDescription) {
        this.krDescription = krDescription;
    }
}
