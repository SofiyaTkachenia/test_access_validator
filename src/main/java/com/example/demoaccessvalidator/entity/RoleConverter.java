package com.example.demoaccessvalidator.entity;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter implements AttributeConverter<List<Role>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        return roles.stream()
                .map(Role::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Role> convertToEntityAttribute(String rolesString) {
        return List.of(rolesString.split(DELIMITER)).stream()
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }
}
