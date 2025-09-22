package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.dto.request.ManualCharacterConfigRequest;
import com.github.syun9274.hsr_calculator.model.Character;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import com.github.syun9274.hsr_calculator.model.enums.FatePath;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import lombok.Builder;

@Builder
public record CharacterDto(
        String name,                     // 캐릭터 명
        int level,                       // 캐릭터 레벨
        int baseHp,                      // 캐릭터 기초 체력
        int baseAtk,                     // 캐릭터 기초 공격력
        int baseDef,                     // 캐릭터 기초 방어력
        Element element,                 // 캐릭터 원소
        FatePath fatePath,               // 운명의 길
        StatType scalingAttribute,         // 메인 계수 (hp, atk, def)
        CharacterAbilityDto basicAttack, // 일반 공격 정보
        CharacterAbilityDto skill,       // 전투 스킬 정보
        CharacterAbilityDto ultimate,    // 필살기 정보
        CharacterAbilityDto[] traces     // 행적 정보
) {
    /**
     * CharacterEntity -> CharacterDto
     */
    public static CharacterDto from(Character character) {
        return new CharacterDto(
                character.getName(),
                character.getLevel(),
                character.getBaseHp(),
                character.getBaseAtk(),
                character.getBaseDef(),
                character.getElement(),
                character.getFatePath(),
                character.getScalingAttribute(),
                null,
                null,
                null,
                null
        );
    }

    /**
     * CharacterEntity -> CharacterDto
     */
    public static CharacterDto from(ManualCharacterConfigRequest request) {
        return new CharacterDto(
                request.getName(),
                request.getLevel(),
                request.getBaseHp(),
                request.getBaseAtk(),
                request.getBaseDef(),
                request.getElement(),
                request.getFatePath(),
                request.getScalingAttribute(),
                request.getBasicAttack(),
                null,
                null,
                null
        );
    }

}
