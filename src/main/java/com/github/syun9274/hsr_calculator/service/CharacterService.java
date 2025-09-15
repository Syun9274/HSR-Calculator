package com.github.syun9274.hsr_calculator.service;

import com.github.syun9274.hsr_calculator.dto.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.request.ManualCharacterConfigRequest;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    public CharacterDto configureCharacterManually(ManualCharacterConfigRequest request) {
        return CharacterDto.from(request);
    }
}
