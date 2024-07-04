package com.lld4.productcatalog.inheritancestrategy.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Instructor extends User {
    private String companyName;
}
