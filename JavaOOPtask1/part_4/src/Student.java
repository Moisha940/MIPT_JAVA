import java.util.*;
import java.util.function.Predicate;

public class Student<T> {
    private String name;
    public List<T> marks = new ArrayList<T>();
    private Predicate<T> condition;
    private List<String> logs = new ArrayList<String>();
    private List<T> deletedMarks = new ArrayList<T>();
    private List<T> addedMarks = new ArrayList<T>();
    private Boolean rememberLog = true;


    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<T> marks) {
        this.name = name;
        this.marks = new ArrayList<>(marks);
    }

    public Student(String name, List<T> marks, Predicate<T> condition) throws Exception {
        this.name = name;
        this.condition = condition;
        for (T mark: marks) {
            if (!condition.test(mark)){
                throw new Exception("У студента " + this.name + " оценка не соответствует условию!");
            }
        }
        this.marks = new ArrayList<>(marks);
    }

    public String getName() {
        return name;
    }

    public List<T> getMarks() {
        return this.marks;
    }

    void setName(String name) {
        if (rememberLog) {
            logs.add(this.name);
        }
        this.name = name;
    }

    void addMark(T mark) throws Exception {
        if (condition != null && !condition.test(mark)){
            throw new Exception("У студента " + this.name + " оценка не соответствует условию!");
        }
        if (rememberLog) {
            logs.add("add");
            this.addedMarks.add(mark);
        }
        this.marks.add(mark);
    }

    void deleteMark(T mark) {
        if (rememberLog) {
            logs.add("delete");
            this.deletedMarks.add(mark);
        }
        this.marks.remove(mark);
    }

    void rollback() throws Exception {
        if (logs.isEmpty()) {
            System.out.println("Вы пришли к начальному состоянию!");
            return;
        }
        this.rememberLog = false;
        if (logs.getLast().equals("add")) {
            this.deleteMark(this.addedMarks.removeLast());
            logs.removeLast();
        } else if (logs.getLast().equals("delete")) {
            this.addMark(this.deletedMarks.removeLast());
            logs.removeLast();
        } else {
            this.setName(logs.removeLast());
        }
        this.rememberLog = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) return false;
        Student s = (Student) obj;
        if (this.marks == null && s.marks == null) {return this.name.equals(s.name);}
        if (!this.name.equals(s.name)) return false;
        assert this.marks != null;
        return this.marks.equals(s.marks);
    }

    @Override
    public String toString() {
        if (this.marks != null) {
            StringBuilder string_marks = new StringBuilder();
            for (T mark : this.marks) {
                string_marks.append(mark.toString()).append(", ");
            }
            return "Имя: " + this.name + " [" + string_marks + "]";
        } else {
            return "Имя: " + this.name;
        }
    }
}
