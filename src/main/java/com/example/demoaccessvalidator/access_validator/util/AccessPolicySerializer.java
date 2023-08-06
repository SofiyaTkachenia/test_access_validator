package com.example.demoaccessvalidator.access_validator.util;

import com.example.demoaccessvalidator.access_validator.AccessPolicy;
import com.example.demoaccessvalidator.access_validator.BoardAction;
import com.example.demoaccessvalidator.access_validator.Statement;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AccessPolicySerializer extends JsonSerializer<AccessPolicy> {
    @Override
    public void serialize(AccessPolicy value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("statements");
        for (Statement statement : value.getStatements()) {
            gen.writeStartObject();
            gen.writeObjectField("principalType", statement.getPrincipalType());
            gen.writeObjectField("principalId", statement.getPrincipalId());
            if (statement.getActions() != null) {
                gen.writeArrayFieldStart("actions");
                for (BoardAction action : statement.getActions()) {
                    gen.writeString(action.name());
                }
                gen.writeEndArray();
            }

            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}