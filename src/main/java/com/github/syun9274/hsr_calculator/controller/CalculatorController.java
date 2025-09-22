package com.github.syun9274.hsr_calculator.controller;

import com.github.syun9274.hsr_calculator.dto.request.ManualDamageCalculationRequest;
import com.github.syun9274.hsr_calculator.dto.response.DamageResult;
import com.github.syun9274.hsr_calculator.facade.GameFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/calculator")
@RestController
public class CalculatorController {

    private final GameFacade gameFacade;

    @PostMapping("/calculate/manual")
    public ResponseEntity<DamageResult> calculate(
            @Valid @RequestBody ManualDamageCalculationRequest request
    ) {
        DamageResult finalDamage = gameFacade.calculateDamageManual(
                request.getCharacter(),
                request.getEnemy(),
                request.getCharacterBuffs(),
                request.getEnemyBuffs());

        return ResponseEntity.ok().body(finalDamage);
    }

}
