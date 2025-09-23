package com.github.syun9274.hsr_calculator.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * BuffDto 리스트를 JSON 문자열로 변환하는 JPA 컨버터
 * <p>
 * 스킬/필살기에 여러 버프가 붙을 때 데이터베이스에 저장하기 위해 사용
 */
@Converter
public class BuffListConverter implements AttributeConverter<List<BuffDto>, String> {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 엔티티의 BuffDto 리스트를 데이터베이스 저장용 JSON 문자열로 변환
     *
     * @param buffs 변환할 BuffDto 리스트
     * @return JSON 형태의 문자열, 변환 실패 시 빈 배열 "[]"
     */
    @Override
    public String convertToDatabaseColumn(List<BuffDto> buffs) {
        try {
            return mapper.writeValueAsString(buffs);
        } catch (Exception e) {
            return "[]";
        }
    }

    /**
     * 데이터베이스의 JSON 문자열을 BuffDto 리스트로 변환
     *
     * @param json 데이터베이스에서 읽어온 JSON 문자열
     * @return BuffDto 리스트, 변환 실패 시 빈 리스트
     */
    @Override
    public List<BuffDto> convertToEntityAttribute(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<BuffDto>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
