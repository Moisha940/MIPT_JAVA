import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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

        Student<Integer> student1 = new Student<>("Moisha");
        Student<Integer> student2 = new Student<>("Maksim", marks1);
        Student<String> student3 = new Student<>("Dima", marks2);
        Student<Integer> student4 = new Student<>("Lida", marks3, x-> x % 2 != 0);
        Student<Integer> student5 = new Student<>("Ulea", marks1, x-> x % 2 == 0);

        int j = 1;
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(1);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(2);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(3);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(4);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(4);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(5);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.deleteMark(1);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.deleteMark(3);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.deleteMark(4);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.deleteMark(2);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(6);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        student2.addMark(7);
        Collections.sort(student2.marks);
        System.out.println(j + " " + student2);
        ++j;
        System.out.println("---------------------------");

        for (int i = 13; i >= 0; --i) {
            System.out.println(i + " " + student2);
            student2.rollback();
            Collections.sort(student2.marks);
        }
    }
}