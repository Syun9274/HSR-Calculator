package com.github.syun9274.hsr_calculator.service;

import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.dto.request.ManualEnemyConfigRequestDto;
import org.springframework.stereotype.Service;

@Service
public class EnemyService {

    public EnemyDto configureEnemyManually(ManualEnemyConfigRequestDto request) {
        return EnemyDto.from(request);
    }
}
