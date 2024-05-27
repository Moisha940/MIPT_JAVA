package ru.moisha.spinghw.dao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import ru.moisha.spinghw.entity.Person;
import ru.moisha.spinghw.entity.University;
import ru.moisha.spinghw.repository.PersonRepository;
import ru.moisha.spinghw.repository.UniversityRepository;

import java.util.List;

@Component
@RequiredArgsConstructor

public class Dao {
    private final PersonRepository personRepository;
    private final UniversityRepository universityRepository;

    public void add(Person person, List<String> universityData) {
        if (!universityData.get(0).equals("0")) {
            // если университет уже есть мы должны его получить
            University university = universityRepository.findById(Long.valueOf(universityData.get(0)))
                    .orElseThrow(() -> new RuntimeException("University not found!"));
            university.setUniversityname(universityData.get(1));
            university.addPerson(person);
        } else {
            // иначе создать
            University university = new University();
            university.setUniversityname(universityData.get(1));
            university.addPerson(person);
            universityRepository.save(university);
        }
        personRepository.save(person);
    }
}