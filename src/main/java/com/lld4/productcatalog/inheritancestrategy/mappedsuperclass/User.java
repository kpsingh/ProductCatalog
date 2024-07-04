package com.lld4.productcatalog.inheritancestrategy.mappedsuperclass;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    @Id
    private int id;
    private String name;
}
