package com.github.syun9274.hsr_calculator.dto.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManualDamageCalculationRequestDto {

    @Valid
    private ManualCharacterConfigRequestDto character;

    @Valid
    private ManualEnemyConfigRequestDto enemy;

    @Valid
    private BuffListRequestDto characterBuffs;

    @Valid
    private BuffListRequestDto enemyBuffs;
}