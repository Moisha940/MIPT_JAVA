package TestObjects;

import Annotations.Cache;
import Annotations.Setter;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {}

    @Cache
    public String getName() {
        return name;
    }

    @Cache
    public int getAge() {
        return age;
    }

    @Setter
    public void setName(String name) {
        this.name = name;
    }

    @Setter
    public void setAge(int age) {
        this.age = age;
    }
}
