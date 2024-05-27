import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> marks1 = new ArrayList<>();
        List<String> marks2 = new ArrayList<>();
        List<Integer> marks3 = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            if (i % 2 == 0) {
                marks1.add(i);
            }
            if (i % 2 != 0) {
                marks3.add(i);
            }
        }
        marks2.add("A");
        marks2.add("A+");
        marks2.add("B");

        Student student1 = new Student("Moisha");
        Student<Integer> student2 = new Student<>("Maksim", marks1);
        Student<String> student3 = new Student<>("Dima", marks2);
        Student<Integer> student4 = new Student<>("Lida", marks3, x -> x % 2 != 0);
        Student<Integer> student5 = new Student<>("Ulea", marks1, x -> x % 2 == 0);
    }
}