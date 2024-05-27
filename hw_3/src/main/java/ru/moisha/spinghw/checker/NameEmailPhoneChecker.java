package ru.moisha.spinghw.checker;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class NameEmailPhoneChecker {

    public boolean check(String email, String phone) {
        return emailChecker(email) && phoneChecker(phone);
    }

    public boolean emailChecker(String email) {
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        return emailPattern.matcher(email).matches();
    }

    public boolean phoneChecker(String phone) {
        Pattern phonePattern = Pattern.compile("\\+[0-9]{11}");
        return phonePattern.matcher(phone).matches();
    }

    public String correctName(String name) {
        StringBuilder correctName = new StringBuilder();
        for (String s : name.toLowerCase().split(" ")) {
            correctName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
        }
        correctName.deleteCharAt(correctName.length() - 1);
        return correctName.toString();
    }
}