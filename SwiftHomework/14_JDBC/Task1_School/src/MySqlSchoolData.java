
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlSchoolData {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

    public MySqlSchoolData(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;
    }

    void insertTeacher(Teacher teacher) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO `school`.`teachers` (`name`,`email`,`salary`) VALUES (?, ?, ?);")) {

            statement.setString(1, teacher.name);
            statement.setString(2, teacher.email);
            statement.setDouble(3, teacher.salary);

            statement.execute();
        }
    }

    Teacher getTeacher(int teacherId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * FROM `school`.`teachers` WHERE id = ?")) {

            statement.setInt(1, teacherId);

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return new Teacher(rs.getString("name"), rs.getString("email"), rs.getDouble("salary"));
            }
        }
    }

    List<Teacher> getTeachersWithSalary(int lowerBound, int upperBound) throws SQLException {
        List<Teacher> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * FROM `school`.`teachers` WHERE salary BETWEEN ? AND ?")) {

            statement.setInt(1, lowerBound);
            statement.setInt(2, upperBound);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(new Teacher(rs.getString("name"), rs.getString("email"), rs.getDouble("salary")));
                }
            }

            return list;
        }
    }

    void insertStudent(Student student) throws SQLException {

        int addressId = insertAddress(student.address);

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO `school`.`students` (`name`,`enrollmentDate`, `address_id`) VALUES(?, ?, ?);")) {

            statement.setString(1, student.name);
            statement.setDate(2, Date.valueOf(student.enrollmentDate));
            statement.setInt(3, addressId);

            statement.execute();
        }
    }

    Student getStudent(int studentId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * FROM students JOIN address ON students.address_id = address.id "
                        + "WHERE students.id = ?;")) {
            statement.setInt(1, studentId);

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                Address address = new Address(
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("street"),
                        rs.getString("number"),
                        rs.getInt("floor"),
                        rs.getInt("apartmentNo"));

                return new Student(rs.getString("name"), rs.getDate("enrollmentDate").toLocalDate(), address);
            }
        }
    }

    List<String> getDisciplinesByTeacherId(int teacherId) throws SQLException {

        List<String> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT disciplines.name FROM disciplines_taught "
                        + "JOIN disciplines ON disciplines_taught.discipline_id = disciplines.id "
                        + "WHERE disciplines_taught.teacher_id = ?")) {

            statement.setInt(1, teacherId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("name"));
                }
            }

            return list;
        }
    }

    List<Teacher> getTeachersByDisciplineName(String disciplineName) throws SQLException {

        List<Teacher> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT teachers.* FROM disciplines_taught "
                        + "JOIN disciplines ON disciplines_taught.discipline_id = disciplines.id "
                        + "JOIN teachers ON teachers.id = disciplines_taught.teacher_id "
                        + "WHERE disciplines.name = ?")) {

            statement.setString(1, disciplineName);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(new Teacher(rs.getString("name"), rs.getString("email"), rs.getDouble("salary")));
                }
                
                return list;
            }
        }
    }

    private int insertAddress(Address address) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO `school`.`address` (`country`,`city`,`street`,`number`,`floor`,`apartmentNo`) "
                        + "VALUES (?, ?, ?, ?, ?, ?);")) {

            statement.setString(1, address.country);
            statement.setString(2, address.city);
            statement.setString(3, address.street);
            statement.setString(4, address.number);
            statement.setInt(5, address.floor);
            statement.setInt(6, address.apartmentNo);

            statement.execute();

            try (ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();")) {
                rs.next();
                return rs.getInt(1);
            }
        }

    }
}
