package com.example.demoaccessvalidator.entity;

import com.example.demoaccessvalidator.access_validator.AccessPolicy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "boards")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "access_policy")
    private AccessPolicy accessPolicy;
}
