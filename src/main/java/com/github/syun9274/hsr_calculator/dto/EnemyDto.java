package com.github.syun9274.hsr_calculator.dto;

import com.github.syun9274.hsr_calculator.dto.request.ManualEnemyConfigRequest;
import com.github.syun9274.hsr_calculator.model.Enemy;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import lombok.Builder;

import java.util.List;

@Builder
public record EnemyDto(
        String name,
        int level,
        int baseHp,
        int baseDef,
        List<Element> weaknessElements,
        List<Element> resistElements,
        boolean isBroken
) {
    /**
     * EnemyEntity -> EnemyDto
     */
    public static EnemyDto from(Enemy entity) {
        return new EnemyDto(
                entity.getName(),
                entity.getLevel(),
                entity.getBaseHp(),
                entity.getBaseDef(),
                entity.getWeaknessElements(), // 약점 속성
                entity.getResistElements(),   // 저항 속성 (존재하지 않을 수 있음)
                false // default - false, 격파 안됨, 강인성 존재함
        );
    }

    /**
     * ManualEnemyConfigRequest -> EnemyDto
     */
    public static EnemyDto from(ManualEnemyConfigRequest request) {
        return new EnemyDto(
                request.getName(),
                request.getLevel(),
                request.getBaseHp(),
                request.getBaseDef(),
                request.getWeaknessElements(),
                request.getResistanceElements(),
                request.isBroken()
        );
    }
}

