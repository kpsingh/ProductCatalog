package com.lld4.productcatalog.inheritancestrategy.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_instructor")
public class Instructor extends User {
    private String companyName;
}
