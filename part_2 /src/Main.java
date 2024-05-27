import TestObjects.UberMan;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        UberMan uber = new UberMan("Вася", 23, 100_000);

        UberMan userProxy = (UberMan) Utils.cache(uber);

        System.out.println(uber.getName());

        System.out.println(userProxy.getAge());
        System.out.println(userProxy.getAge());

        System.out.println(userProxy.getSalary());
        System.out.println(userProxy.getSalary());
        userProxy.setSalary(100_000);
        System.out.println(userProxy.getSalary());
        System.out.println(userProxy.getSalary());

        System.out.println(userProxy.getName());

        userProxy.sayHello();
        userProxy.sayHello();

        userProxy.eating();
        userProxy.eating();
    }
}

