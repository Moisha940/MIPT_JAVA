package ru.moisha.spinghw.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "information")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "university_id")
    private Long university_id;
}


