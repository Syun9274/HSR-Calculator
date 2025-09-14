package com.github.syun9274.hsr_calculator.dto.request;

import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.BuffType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BuffListRequestDto {

    @Valid
    private List<BuffRequestDto> buffs = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BuffRequestDto {

        @NotNull
        private BuffType buffType;

        @DecimalMin(value = "0.0")
        private double buffValue;
    }

    public List<BuffDto> toBuffList() {
        return buffs.stream()
                .map(dto -> new BuffDto(dto.getBuffType(), dto.getBuffValue()))
                .toList();
    }
}
