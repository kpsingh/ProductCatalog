package com.lld4.productcatalog.inheritancestrategy.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class TA extends User {
    private double rating;
}
