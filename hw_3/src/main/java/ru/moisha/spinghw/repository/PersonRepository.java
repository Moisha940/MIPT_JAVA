package ru.moisha.spinghw.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.moisha.spinghw.entity.Person;

@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {

}