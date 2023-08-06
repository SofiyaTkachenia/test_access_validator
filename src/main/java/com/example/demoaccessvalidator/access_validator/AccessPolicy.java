package com.example.demoaccessvalidator.access_validator;

import com.example.demoaccessvalidator.access_validator.util.AccessPolicyDeserializer;
import com.example.demoaccessvalidator.access_validator.util.AccessPolicySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonSerialize(using = AccessPolicySerializer.class)
@JsonDeserialize(using = AccessPolicyDeserializer.class)
public class AccessPolicy {
    private List<Statement> statements;
}

