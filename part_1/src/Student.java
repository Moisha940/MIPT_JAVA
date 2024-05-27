public class Student implements Able {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {}

    @Cache
    @Override
    public String getName() {
        return name;
    }

    @Cache
    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    @Setter
    public void setName(String name) {
        this.name = name;
    }

    @Setter
    @Override
    public  void setAge(int age) {
        this.age = age;
    }

    public String mood() {
        return "sad";
    }
}

