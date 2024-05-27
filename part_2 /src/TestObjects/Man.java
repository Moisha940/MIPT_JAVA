package TestObjects;

import Annotations.Cache;
import Annotations.Setter;

public class Man extends Person implements IUser {
    int salary;

    public Man(String name, int age, int salary) {
        super(name, age);
        this.salary = salary;
    }

    public Man() {}

    @Cache
    @Override
    public int getSalary() {
        return salary;
    }

    @Setter
    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getName() {
        return super.getName() + " " + "Пупкин";
    }
}