package com.arsen.exchange.model;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Where(clause = "deleted = false")
public class Item extends BaseEntity {


    @Column(unique = true)
    private String name;


    public Item() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}