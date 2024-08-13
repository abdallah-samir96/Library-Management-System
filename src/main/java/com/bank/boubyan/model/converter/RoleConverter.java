package com.bank.boubyan.model.converter;

import com.bank.boubyan.model.domain.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class RoleConverter implements AttributeConverter<List<Role>, String> {
    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        try {
            return new ObjectMapper().writeValueAsString(roles);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> convertToEntityAttribute(String s) {
        try {
            return new ObjectMapper().readValue(s, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
