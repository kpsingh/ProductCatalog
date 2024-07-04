package com.lld4.productcatalog.inheritancestrategy.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_instructor")
public class Instructor extends User{
    private String companyName;
}
