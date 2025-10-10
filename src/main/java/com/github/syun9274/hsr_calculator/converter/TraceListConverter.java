package com.github.syun9274.hsr_calculator.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syun9274.hsr_calculator.dto.Trace;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter
public class TraceListConverter implements AttributeConverter<List<Trace>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Trace> traces) {
        if (traces == null || traces.isEmpty()) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(traces);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert traces to JSON", e);
        }
    }

    @Override
    public List<Trace> convertToEntityAttribute(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<Trace>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to traces", e);
        }
    }
}
