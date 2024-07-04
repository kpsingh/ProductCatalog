package com.lld4.productcatalog.inheritancestrategy.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Mentor extends User {
    private long hours;
}
