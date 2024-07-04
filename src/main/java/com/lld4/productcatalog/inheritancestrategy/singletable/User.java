package com.lld4.productcatalog.inheritancestrategy.singletable;

import jakarta.persistence.*;

@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class User {
    @Id
    private int id;
    private String name;
}
