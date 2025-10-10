package com.github.syun9274.hsr_calculator.dto.request;

import com.github.syun9274.hsr_calculator.dto.Buff;
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
public class BuffListRequest {

    @Valid
    private List<BuffRequestDto> buffs = new ArrayList<>();

    public record BuffRequestDto(
            @NotNull
            BuffType buffType,

            @DecimalMin(value = "0.0")
            double buffValue
    ) {}

    public List<Buff> toBuffList() {
        return buffs.stream()
                .map(dto -> new Buff(dto.buffType, dto.buffValue))
                .toList();
    }
}
