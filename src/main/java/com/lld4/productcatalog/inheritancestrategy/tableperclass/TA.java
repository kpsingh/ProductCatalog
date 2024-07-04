package com.lld4.productcatalog.inheritancestrategy.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User{
    private double rating;
}
