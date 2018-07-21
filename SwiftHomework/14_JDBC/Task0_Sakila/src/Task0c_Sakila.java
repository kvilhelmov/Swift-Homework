
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task0c_Sakila {

    static final String DB_CONN_STRING = "jdbc:mysql://192.168.164.3:3306/sakila";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "swift12345";

    static final String INSERT_ADDRESS = "INSERT INTO `sakila`.`address` (`address`,`address2`, `district`,`city_id`,`postal_code`,`phone`,`location`)"
            + "VALUES (?, ?, ?, ?, ?, ?, ST_PointFromText(?));";

    static final String INSERT_CUSTOMER = "INSERT INTO `sakila`.`customer` (`store_id`, `first_name`,`last_name`,`email`,`address_id`) "
            + "VALUES (?, ?, ?, ?, ?);";

    static int insertAddress(Address address) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(INSERT_ADDRESS)) {
            statement.setString(1, address.address1);
            statement.setString(2, address.address2);
            statement.setString(3, address.district);
            statement.setInt(4, address.cityId);
            statement.setString(5, address.postalCode);
            statement.setString(6, address.phone);
            statement.setString(7, String.format("POINT(%f %f)", address.longitude, address.latitude));

            statement.execute();
            try (ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()")) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    static void insertCustomer(String firstName, String lastName, String email, Address address, int storeId) throws SQLException {

        int addressId = insertAddress(address);

        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(INSERT_CUSTOMER)) {

            statement.setInt(1, storeId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setInt(5, addressId);

            statement.execute();
        }
    }

    public static void main(String[] args) throws SQLException {
        Address address = new Address();
        address.address1 = "1312 adsda Street";
        address.address2 = null;
        address.cityId = 1;
        address.district = "Unknown";
        address.phone = "001123456789";
        address.longitude = 44.121;
        address.latitude = -39.132;

        //insertCustomer("Lyubomir", "Ivanov", "livanov@swift.bg", address, 1);
    }
}
