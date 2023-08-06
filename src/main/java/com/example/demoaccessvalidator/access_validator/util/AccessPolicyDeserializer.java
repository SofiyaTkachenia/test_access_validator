package com.example.demoaccessvalidator.access_validator.util;

import com.example.demoaccessvalidator.access_validator.AccessPolicy;
import com.example.demoaccessvalidator.access_validator.Statement;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccessPolicyDeserializer extends JsonDeserializer<AccessPolicy> {
    @Override
    public AccessPolicy deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        JsonNode statements = node.get("statements");

        List<Statement> statementList = new ArrayList<>();
        if (statements != null && statements.isArray()) {
            for (JsonNode statementNode : statements) {
                Statement statement = mapper.convertValue(statementNode, Statement.class);
                statementList.add(statement);
            }
        }

        return new AccessPolicy().setStatements(statementList);
    }
}
