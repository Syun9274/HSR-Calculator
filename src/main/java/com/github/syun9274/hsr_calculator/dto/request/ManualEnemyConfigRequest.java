package com.github.syun9274.hsr_calculator.dto.request;

import com.github.syun9274.hsr_calculator.model.enums.Element;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class ManualEnemyConfigRequest {

    private String name;

    @Min(value = 1, message = "레벨은 1 이상이어야 합니다.")
    private int level;

    @Min(value = 1, message = "기초 체력은 1 이상이어야 합니다.")
    private int baseHp;

    @Min(value = 0, message = "기초 방어력은 0 이상이어야 합니다.")
    private int baseDef;

    private int spd;

    @NotNull(message = "약점 속성 목록은 필수입니다.")
    @Size(min = 1, message = "최소 1개 이상의 약점 속성이 필요합니다.")
    private List<Element> weaknessElements;

    private List<Element> resistanceElements;

    boolean isBroken; // default = false

}
