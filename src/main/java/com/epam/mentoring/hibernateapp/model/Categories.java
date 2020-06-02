package com.epam.mentoring.hibernateapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NamedQuery(name = "Categories.byName", query = " SELECT a FROM Categories a order by a.name asc")
@Data
@Entity
@EqualsAndHashCode(exclude = "amount", callSuper = true)
public class Categories extends AbstractIdentifiableObject {
    private String name;
    private Long amount;
    @OneToMany(mappedBy = "categories", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Products> products = new ArrayList<>();

    public Categories(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }
}
