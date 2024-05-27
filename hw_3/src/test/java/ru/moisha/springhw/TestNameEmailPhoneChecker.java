package ru.moisha.springhw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.moisha.spinghw.checker.NameEmailPhoneChecker;

public class TestNameEmailPhoneChecker {
    //@ParameterizedTest
    //@RepeatedTest(10)
    //@MethodSource()
    @Test
    @DisplayName( "testing Phone")
    public void testPhone() {
        NameEmailPhoneChecker nameEmailPhoneChecker = new NameEmailPhoneChecker();
        Assertions.assertTrue(nameEmailPhoneChecker.phoneChecker("+88888888888"), "Phone checker failed");
    }

    @Test
    @DisplayName( "testing Email")
    public void testEmail() {
        NameEmailPhoneChecker nameEmailPhoneChecker = new NameEmailPhoneChecker();
        Assertions.assertTrue(nameEmailPhoneChecker.emailChecker("qwerty@maiewewew.ru"), "Email checker failed");
    }

}
