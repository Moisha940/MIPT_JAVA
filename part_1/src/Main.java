import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        //Student s2 = new Student("Robert", 22);

        Able a = Utils.cache(new Student("Moisha", 23));

        System.out.println(a.getName());
        System.out.println("\n");

        System.out.println(a.getName());
        System.out.println("\n");

        System.out.println(a.getAge());
        System.out.println("\n");

        a.setName("Misha");
        
        a.setAge(24);


        System.out.println(a.getAge());
        System.out.println("\n");

        System.out.println(a.getName());
        System.out.println("\n");

        System.out.println(a.getAge());
        System.out.println("\n");

        System.out.println(a.getName());
        System.out.println("\n");

    }
}