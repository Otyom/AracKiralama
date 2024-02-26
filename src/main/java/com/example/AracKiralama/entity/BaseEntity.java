package com.example.AracKiralama.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity {
    String createDate;
    String updateDate;
    int status;
}
