
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Task1_School {
    
    static final String DB_CONN_STRING = "jdbc:mysql://192.168.164.3:3306/school";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "swift12345";
    
    public static void main(String[] args) throws SQLException {
        MySqlSchoolData schoolDataStorage = new MySqlSchoolData(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);

        //testInsertTeacher(schoolDataStorage);
        //testGetTeacher(schoolDataStorage);
        //testGetTeacherWithSalaryInRange(schoolDataStorage);
        //testInsertStudent(schoolDataStorage);
        //testGetStudent(schoolDataStorage);
        //testGetDisciplinesByTeacherId(schoolDataStorage);
        testGetTeachersByDisciplineName(schoolDataStorage);
    }
    
    private static void testInsertTeacher(MySqlSchoolData schoolDataStorage) throws SQLException {
        schoolDataStorage.insertTeacher(new Teacher("Pesho", "peter@petrov.bg", 1818.18));
    }
    
    private static void testGetTeacher(MySqlSchoolData schoolDataStorage) throws SQLException {
        Teacher teacher = schoolDataStorage.getTeacher(1);
        System.out.println(teacher);
    }
    
    private static void testGetTeacherWithSalaryInRange(MySqlSchoolData schoolDataStorage) throws SQLException {
        List<Teacher> teachers = schoolDataStorage.getTeachersWithSalary(1400, 1900);
        teachers.forEach((teacher) -> {
            System.out.println(teacher);
        });
    }
    
    private static void testInsertStudent(MySqlSchoolData schoolDataStorage) throws SQLException {
        schoolDataStorage.insertStudent(
                new Student("Ivan Ivanov",
                        LocalDate.of(2012, 10, 15),
                        new Address("Bulgaria", "Sofia", "Shipchenski Prohod", "12", 4, 3)));
    }
    
    private static void testGetStudent(MySqlSchoolData schoolDataStorage) throws SQLException {
        Student student = schoolDataStorage.getStudent(5);
        System.out.println(student);
    }
    
    private static void testGetDisciplinesByTeacherId(MySqlSchoolData schoolDataStorage) throws SQLException {
        List<String> disciplines = schoolDataStorage.getDisciplinesByTeacherId(1);
        disciplines.forEach(discipline -> System.out.println(discipline));
    }

    private static void testGetTeachersByDisciplineName(MySqlSchoolData schoolDataStorage) throws SQLException {
        List<Teacher> teachers = schoolDataStorage.getTeachersByDisciplineName("Mathematics");
        teachers.forEach(teacher -> System.out.println(teacher));
    }
    
}
