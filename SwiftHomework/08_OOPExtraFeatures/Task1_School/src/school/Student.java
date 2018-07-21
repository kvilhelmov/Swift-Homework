package school;

public class Student extends Person {

    private int id;
    private Class attendingClass;

    public Student(Class cls) {
        attendingClass = cls;
        this.id = attendingClass.addStudent(this);
    }

    public void setClass(Class cls) {
        this.attendingClass = cls;
    }

    int getId() {
        return id;
    }
}
