package com.github.syun9274.hsr_calculator.controller;

import com.github.syun9274.hsr_calculator.dto.request.BuffListRequestDto;
import com.github.syun9274.hsr_calculator.dto.request.ManualCharacterConfigRequestDto;
import com.github.syun9274.hsr_calculator.dto.request.ManualEnemyConfigRequestDto;
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
    public ResponseEntity<?> calculate(
            @Valid @RequestBody ManualCharacterConfigRequestDto characterReq,
            @Valid @RequestBody ManualEnemyConfigRequestDto enemyReq,
            @Valid @RequestBody BuffListRequestDto charBuffListReq,
            @Valid @RequestBody BuffListRequestDto enemyBuffListReq
    ) {
        int finalDamage = gameFacade.calculateDamageManual(
                characterReq, enemyReq, charBuffListReq, enemyBuffListReq);

        return ResponseEntity.ok().body(finalDamage);
    }

}
