package com.grechur.deskpet;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Student {
    @Id
    long id;

    String name;

    int age;
}
