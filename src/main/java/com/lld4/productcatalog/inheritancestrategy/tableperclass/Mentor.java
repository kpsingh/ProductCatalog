package com.lld4.productcatalog.inheritancestrategy.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User{
    private long hours;
}
