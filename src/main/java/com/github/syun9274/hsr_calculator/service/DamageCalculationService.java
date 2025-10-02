package com.github.syun9274.hsr_calculator.service;

import com.github.syun9274.hsr_calculator.calculator.DamageCalculator;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.dto.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.model.enums.DamageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DamageCalculationService {

    private final DamageCalculator damageCalculator;

    public Map<DamageType, Integer> calculateDamage(CharacterDto character, EnemyDto enemy,
                                                    List<BuffDto> charBuffs, List<BuffDto> enemyBuffs,
                                                    boolean isBroken) {
        return damageCalculator.calculateFinalDamage(
                character, enemy, charBuffs, enemyBuffs, isBroken);
    }
}
