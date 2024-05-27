import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Student<T> {
    private String name;
    private List<T> marks = new ArrayList<T>();
    private Predicate<T> condition;

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
        this.name = name;
    }

    void addMark(T mark) throws Exception {
        if (condition != null && !condition.test(mark)){
            throw new Exception("У студента " + this.name + " оценка не соответствует условию!");
        }
        this.marks.add(mark);
    }

    void deleteMark(T mark) {
        this.marks.remove(mark);
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