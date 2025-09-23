package com.github.syun9274.hsr_calculator.model;

import com.github.syun9274.hsr_calculator.converter.ElementListConverter;
import com.github.syun9274.hsr_calculator.model.base.BaseEntity;
import com.github.syun9274.hsr_calculator.model.enums.Element;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
public class Enemy extends BaseEntity {

    // 약점 속성
    @Convert(converter = ElementListConverter.class)
    private List<Element> weaknessElements;

    // 저항 속성
    @Convert(converter = ElementListConverter.class)
    private List<Element> resistElements;

}
