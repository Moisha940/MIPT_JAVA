import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> marks = null;

    Student(String name, List<Integer> marks) {
        this.name = name;
        this.marks = new ArrayList<>(marks);
    }


    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    List<Integer> getMarks() {
        return this.marks;
    }

    void setName(String name) {
        this.name = name;
    }

    void addMark(Integer mark) {
        this.marks.add(mark);
    }

    void deleteMark(Integer mark) {
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
            for (Integer mark : this.marks) {
                string_marks.append(mark.toString());
            }
            return "Имя: " + this.name + "[" + string_marks + "]";
        } else {
            return "Имя: " + this.name;
        }
    }
}