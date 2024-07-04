package com.lld4.productcatalog.inheritancestrategy.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_mentor")
public class Mentor extends User {
    private long hours;
}
