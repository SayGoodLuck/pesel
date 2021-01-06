package dev.konstantin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @Column(name = "pesel")
    private String id;
}
