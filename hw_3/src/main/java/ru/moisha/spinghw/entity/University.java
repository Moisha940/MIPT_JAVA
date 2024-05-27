package ru.moisha.spinghw.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "universities")
public class University {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "universityname")
    private String universityname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "university_id")
    private List<Person> persons = new ArrayList<Person>();

    public void addPerson(Person person) {
        persons.add(person);
    }
}