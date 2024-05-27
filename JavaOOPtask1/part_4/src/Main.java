import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Student<String>> group = new LinkedList<>();
        Scanner consoleReader = new Scanner(System.in);
        boolean stopProcess = false;

        while (!stopProcess) {
            System.out.println("~~~~~~~МЕНЮ~~~~~~~");
            System.out.println("1. Создать студента с именем");
            System.out.println("2. Добавить оценку");
            System.out.println("3. Удалить оценку");
            System.out.println("4. Вывести информацию о текущем студенте");
            System.out.println("5. Выход");

            stopProcess =  switch (consoleReader.nextLine()) {
                case "1":
                    Student<String> student = new Student<>(consoleReader.nextLine());
                    group.add(student);
                    System.out.println("----- Создан студент " + group.getLast().getName());
                    yield false;
                case "2":
                    group.getLast().addMark(consoleReader.nextLine());
                    System.out.println("----- Студент " + group.getLast().getName() + " получил оценку " + group.getLast().getMarks().getLast());
                    yield false;
                case "3":
                    String mark = consoleReader.nextLine();
                    if(group.getLast().getMarks().contains(mark)) {
                        System.out.println("----- Удалена оценка " + mark);
                        group.getLast().deleteMark(mark);
                    } else {
                        System.out.println("----- У студента нет такой оценки");
                    }
                    yield false;
                case "4":
                    System.out.println("----- Студент выведен на экран. " + group.getLast());
                    yield false;
                case "5":
                    System.out.println("----- До свидания!");
                    yield true;
                default:
                    System.out.println("Выберите команду из списка!");
                    yield false;
            };
            System.out.println("\n");
        }
    }
}