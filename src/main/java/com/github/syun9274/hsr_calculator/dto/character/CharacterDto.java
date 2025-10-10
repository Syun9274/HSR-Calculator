package com.github.syun9274.hsr_calculator.dto.character;

import com.github.syun9274.hsr_calculator.dto.DamageInfo;
import com.github.syun9274.hsr_calculator.dto.Trace;
import com.github.syun9274.hsr_calculator.dto.request.ManualCharacterConfigRequest;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import com.github.syun9274.hsr_calculator.model.enums.FatePath;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import lombok.Builder;

import java.util.List;

@Builder
public record CharacterDto(
        String name,                // 캐릭터 명
        int level,                  // 캐릭터 레벨
        int baseHp,                 // 캐릭터 기초 체력
        int baseAtk,                // 캐릭터 기초 공격력
        int baseDef,                // 캐릭터 기초 방어력
        Element element,            // 캐릭터 원소
        FatePath fatePath,          // 운명의 길
        StatType scalingAttribute,  // 메인 계수 (hp, atk, def)
        DamageInfo basicAttack,     // 일반 공격 정보
        AbilityDto skill,           // 전투 스킬 정보
        AbilityDto ultimate,        // 필살기 정보
        AbilityDto talent,          // 특성
        List<Trace> trace,          // 행적
        MemospriteDto memosprite    // 기억정령
) {
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
                request.getSkill(),
                request.getUltimate(),
                null,
                null,
                null
        );
    }

}
