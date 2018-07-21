
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Task0b_Sakila {

    static final String DB_CONN_STRING = "jdbc:mysql://192.168.164.3:3306/sakila";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "swift12345";

    static final String ASSOSCIATE_ACTOR_STATEMENT = "INSERT INTO `sakila`.`film_actor` (`actor_id`, `film_id`) "
            + "VALUES (?, ?);";

    static void assosciateActorToMovie(int actorId, int filmId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(ASSOSCIATE_ACTOR_STATEMENT)) {

            statement.setInt(1, actorId);
            statement.setInt(2, filmId);

            statement.execute();
        }
    }

    public static void main(String[] args) throws SQLException {
        //assosciateActorToMovie(1, 51);
    }
}
