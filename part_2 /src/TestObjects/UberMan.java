package TestObjects;

import Annotations.Cache;

public class UberMan extends Man {

    public UberMan(String name, int age, int salary) {
        super(name, age, salary);
    }

    public UberMan() {
        super();
    }

    @Cache
    public void sayHello() {
        System.out.println("Привет!... ты ожидал чего-то другого?");
        ///return 0;
    }

    public void eating() {
        System.out.println("Eating");
    }
}