package ru.moisha.spinghw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.moisha.spinghw.entity.University;

@Repository
public interface UniversityRepository  extends CrudRepository<University, Long> {

}