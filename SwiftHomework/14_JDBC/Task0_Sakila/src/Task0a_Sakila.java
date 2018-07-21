
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Task0a_Sakila {

    static final String DB_CONN_STRING = "jdbc:mysql://192.168.164.3:3306/sakila";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "swift12345";

    static final String INSERT_ACTOR_STATEMENT = "INSERT INTO `sakila`.`actor` (`first_name`, `last_name`) "
            + "VALUES (?, ?);";

    static void insertActor(String firstName, String lastName) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(INSERT_ACTOR_STATEMENT)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            statement.execute();
        }
    }

    public static void main(String[] args) throws SQLException {
        //insertActor("Monicca", "Belucci");
    }
}
