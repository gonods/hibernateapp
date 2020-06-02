package com.epam.mentoring.hibernateapp.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract  class AbstractIdentifiableObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
