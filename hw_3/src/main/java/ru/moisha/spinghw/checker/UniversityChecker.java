package ru.moisha.spinghw.checker;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UniversityChecker {
    private final JdbcTemplate jdbcTemplate;

    public List<String> check(String inputUniversityName) {
        // Проверка есть ли название уника в БД
        // На [0] значение стоит либо 0, либо 1. На втором название, которе надо добавить в БД
        List<String> universityData = universityNameNormalize(inputUniversityName);

        if (universityData.get(0).equals("1")) {
            // если есть, то узнаем id уника
            String query = "SELECT id FROM universities WHERE universityname = ?";
            Integer id = jdbcTemplate.queryForObject(query, Integer.class, universityData.get(1));

            assert id != null;
            universityData.set(0, String.valueOf(id));
        }

        return universityData;
    }

    public List<String> universityNameNormalize(String inputUniversityName) {
        String correctName = inputUniversityName.toUpperCase();
        List<String> universityData = new ArrayList<>();

        String query = "SELECT count(*) FROM universities WHERE universityname = ?";
        Integer count_1 = jdbcTemplate.queryForObject(query, Integer.class, correctName);

        // вдруг введенные данные уже корректный и есть в БД
        if (count_1  > 0) {
            universityData.add(0, "1");
            universityData.add(1, correctName);
            return universityData;
        }

        // если на вход подалось что-то из нескольких слов
        // проверим есть ли одно из них в БД
        String[] universityNames = inputUniversityName.split(" ");
        for (String universityName : universityNames) {
            int count = jdbcTemplate.queryForObject(query, Integer.class, universityName);
            if (count > 0) {
                correctName = universityName;
                universityData.add(0, "1");
                universityData.add(1, correctName);
                return universityData;
            }
        }

        // получим все университеты из БД
        // разделим наше слово на части и будем смотреть есть ли какая-то часть в строке
        List<String> universitiesFromDB=  jdbcTemplate.query("SELECT * FROM universities", new BeanPropertyRowMapper<>(String.class));

        for (String university : universitiesFromDB) {
            for (String inputUniversity : universityNames) {
                if (university.contains(inputUniversity)) {
                    correctName = university;
                    universityData.add(0, "1");
                    universityData.add(1, correctName);
                    return universityData;
                }
            }
        }

        // если такого университета нет, значит надо добавить
        universityData.add("0");
        universityData.add(correctName);
        return universityData;
    }
}