package com.github.syun9274.hsr_calculator.dto.request;

import com.github.syun9274.hsr_calculator.dto.CharacterAbilityDto;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import com.github.syun9274.hsr_calculator.model.enums.FatePath;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ManualCharacterConfigRequestDto {

    private String name;

    @Min(value = 1, message = "레벨은 1 이상이어야 합니다.")
    private int level;

    @Min(value = 1, message = "기초 체력은 1 이상이어야 합니다.")
    private int baseHp;

    @Min(value = 1, message = "기초 공격력은 1 이상이어야 합니다.")
    private int baseAtk;

    @Min(value = 0, message = "기초 방어력은 0 이상이어야 합니다.")
    private int baseDef;

    @NotNull(message = "캐릭터 메인 계수 정보는 필수입니다. (hp, atk, def)")
    private String scalingAttribute;

    @NotNull(message = "캐릭터 속성 정보는 필수입니다.")
    private Element element;

    @NotNull(message = "캐릭터 운명의 길 정보는 필수입니다.")
    private FatePath fatePath;

    private CharacterAbilityDto basicAttack;

}
