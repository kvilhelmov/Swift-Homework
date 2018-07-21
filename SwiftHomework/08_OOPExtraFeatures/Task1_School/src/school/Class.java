package school;


public class Class {

    private String name;

    private Teacher[] teachers;
    private int teacherCount;

    private Student[] students;
    private int studentCount;

    public Class(String name) {
        this.name = name;

        this.teachers = new Teacher[20];
        this.teacherCount = 0;

        this.students = new Student[20];
        this.studentCount = 0;
    }

    public int addStudent(Student newStudent) {
        for (Student student : students) {
            if (student.equals(newStudent)) {
                return student.getId();
            }
        }

        students[studentCount++] = newStudent;
        newStudent.setClass(this);
        return studentCount;
    }

    public void addTeacher(Teacher newTeacher) {
        for (Teacher teacher : teachers) {
            if (teacher.equals(newTeacher)) {
                return;
            }
        }

        teachers[teacherCount++] = newTeacher;
        newTeacher.addClass(this);
    }
}
