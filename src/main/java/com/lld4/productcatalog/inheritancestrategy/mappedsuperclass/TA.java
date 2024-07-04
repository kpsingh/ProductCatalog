package com.lld4.productcatalog.inheritancestrategy.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_ta")
public class TA extends User {
    private double rating;
}
