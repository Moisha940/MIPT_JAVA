package ru.moisha.spinghw.checker;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;
import ru.moisha.spinghw.dao.Dao;
import ru.moisha.spinghw.entity.Person;
import ru.moisha.spinghw.repository.PersonRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Checker {
    private final PersonRepository personRepository;
    private final NameEmailPhoneChecker nameEmailPhoneChecker;
    private final UniversityChecker universityChecker;
    private final Dao dao;

    public void check(List<String> inputData) {
        for (String input : inputData) {
            String[] data = input.split(" ");

            String name = data[0] + " " + data[1] + " " + data[2];
            String phone = data[3];
            String email = data[4];

            StringBuilder universityName = new StringBuilder();
            for (int i = 5; i < data.length; ++i) {
                universityName.append(data[i]).append(" ");
            }
            universityName.delete(universityName.length() - 1, universityName.length());

            if (nameEmailPhoneChecker.check(email, phone)) {
                String correctName =  nameEmailPhoneChecker.correctName(name);

                List<String> universityData =  universityChecker.check(String.valueOf(universityName));

                Person person = new Person();
                person.setName(correctName);
                person.setEmail(email);
                person.setPhone(phone);

                dao.add(person, universityData);

            } else {
                Logger logger = LogManager.getLogger(Checker.class);
                logger.error("Wrong format of phone or email \n\tEmail: {}\n\tPhone: {}", email, phone);
            }
        }
    }
}