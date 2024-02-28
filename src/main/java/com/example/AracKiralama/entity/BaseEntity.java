package com.example.AracKiralama.entity;

import com.example.AracKiralama.entity.enums.Status;
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
   private String createDate;
   private String updateDate;
   private Status status;
}
