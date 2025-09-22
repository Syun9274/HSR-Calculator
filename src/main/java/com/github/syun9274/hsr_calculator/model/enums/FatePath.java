package com.github.syun9274.hsr_calculator.model.enums;

public enum FatePath {

    DESTRUCTION("파멸"),
    HUNT("수렵"),
    ERUDITION("지식"),
    HARMONY("화합"),
    NIHILITY("공허"),
    PRESERVATION("보존"),
    ABUNDANCE("풍요"),
    REMEMBRANCE("기억"),
    ;

    final String krDescription;

    FatePath(String krDescription) {
        this.krDescription = krDescription;
    }
}
