import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        List<Integer> marks1 = new ArrayList<>();
        List<Integer> marks2 = new ArrayList<>();
        List<Integer> marks3 = new ArrayList<>();
        List<Integer> marks4 = new ArrayList<>();
        List<Integer> marks5 = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            marks1.add(i);
            marks2.add(i);
            marks3.add(i);
            marks4.add(i);
            marks5.add(i);
        }

        Student student1 = new Student("Maks", marks1);
        Student student2 = new Student("Dimas");
        Student student3 = new Student("Oleg", marks2);
        Student student4 = new Student("Moisha", marks3);
        Student student5 = new Student("Lida", marks5);
        Student student6 = new Student("Dimas");
        System.out.println(student3.getMarks());
        student3.addMark(10);
        student3.addMark(11);
        System.out.println(student3.getMarks());
        student3.deleteMark(0);
        student3.deleteMark(1);
        System.out.println(student3.getMarks());
        System.out.println(student6.equals(student2));
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        student1.addMark(10);
        System.out.println(student1);
        student1.addMark(11);
        System.out.println(student1);
    }
}