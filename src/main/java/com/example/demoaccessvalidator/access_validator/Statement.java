package com.example.demoaccessvalidator.access_validator;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Statement {
    private final PrincipalType principalType;
    private final UUID principalId;
    private final List<BoardAction> actions;
}
