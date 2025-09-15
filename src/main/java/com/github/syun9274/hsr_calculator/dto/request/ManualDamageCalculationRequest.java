package com.github.syun9274.hsr_calculator.dto.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManualDamageCalculationRequest {

    @Valid
    private ManualCharacterConfigRequest character;

    @Valid
    private ManualEnemyConfigRequest enemy;

    @Valid
    private BuffListRequest characterBuffs;

    @Valid
    private BuffListRequest enemyBuffs;
}