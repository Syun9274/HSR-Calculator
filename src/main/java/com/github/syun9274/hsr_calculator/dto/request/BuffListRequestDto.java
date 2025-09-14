package com.github.syun9274.hsr_calculator.dto.request;

import com.github.syun9274.hsr_calculator.dto.BuffDto;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

public class BuffListRequestDto {

    @Valid
    private List<BuffDto> buffDtos = new ArrayList<>();

    public List<BuffDto> toBuffList() {
        return buffDtos.stream()
                .map(dto -> new BuffDto(dto.getBuffType(), dto.getBuffValue()))
                .toList();
    }
}
