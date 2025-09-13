package com.github.syun9274.hsr_damage_calculator.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syun9274.hsr_damage_calculator.model.enums.Element;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA AttributeConverter: List<Element>를 JSON 문자열로 변환
 * <p>
 * - List<Element>를 데이터베이스의 VARCHAR 컬럼에 JSON 형태로 저장
 * <p>
 * - 데이터베이스의 JSON 문자열을 다시 List<Element>로 변환
 * <p>
 * 예시
 * <p>
 * Java: [FIRE, ICE, LIGHTNING]
 * <p>
 * DB: '["FIRE","ICE","LIGHTNING"]'
 */
@Slf4j
@Converter
public class ElementListConverter implements AttributeConverter<List<Element>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Java 객체 → 데이터베이스 저장용 문자열 변환
     *
     * @param elements Java의 Element 리스트
     * @return JSON 문자열 (예: '["FIRE","ICE"]') 또는 null
     */
    @Override
    public String convertToDatabaseColumn(List<Element> elements) {
        if (elements == null || elements.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            log.error("Element를 JSON으로 convert 하는 것을 실패했습니다.", e);
            return null;
        }
    }

    /**
     * 데이터베이스 문자열 → Java 객체 변환
     *
     * @param dbData 데이터베이스의 JSON 문자열
     * @return Element 리스트 또는 빈 리스트
     */
    @Override
    public List<Element> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<Element>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("JSON을 Element 리스트로 convert 하는 것을 실패했습니다.", e);
            return new ArrayList<>();
        }
    }
}
