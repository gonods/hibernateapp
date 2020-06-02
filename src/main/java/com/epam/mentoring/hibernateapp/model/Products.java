package com.epam.mentoring.hibernateapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Products.findByCategoryId", query = "from Products where category_id = :categoryId")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@Entity
@EqualsAndHashCode(exclude = "price")
public class Products {
    @EmbeddedId
    private ProductId id;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Embedded
    private Manufacturer manufacturer;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Categories categories;
    @Version
    private int version;

    public Products(ProductId id, Double price, Condition condition, Manufacturer manufacturer, Categories categories) {
        this.id = id;
        this.price = price;
        this.condition = condition;
        this.manufacturer = manufacturer;
        this.categories = categories;
    }
}
