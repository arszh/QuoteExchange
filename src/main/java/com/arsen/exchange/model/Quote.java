package com.arsen.exchange.model;


import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Where(clause = "deleted = false")
public class Quote extends BaseEntity {


    @Column(unique = true)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @PositiveOrZero(message = "The value must be positive")
    private double price;
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 2, max = 2)
    private List<Item> items;


    public Quote() {
    }

    public Quote(String name, double price, List<Item> items) {

        this.name = name;
        this.price = price;
        this.items = items;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
