
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlAddressData {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

    public MySqlAddressData(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;
    }

    String getFullAddress(int addressId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * FROM addresses a "
                        + "JOIN streets b ON a.street_id = b.id "
                        + "JOIN municipalities c ON b.municipality_id = c.id "
                        + "JOIN populated_areas d ON c.populated_area_id = d.id "
                        + "JOIN regions e ON d.region_id = e.id "
                        + "JOIN countries f ON e.country_id = f.id "
                        + "WHERE a.id = ?;")) {

            statement.setInt(1, addressId);

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return convertToAddress(rs);
            }
        }
    }

    List<String> getAddresses(String municipalityName) throws SQLException {

        List<String> addresses = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * FROM addresses a "
                        + "JOIN streets b ON a.street_id = b.id "
                        + "JOIN municipalities c ON b.municipality_id = c.id "
                        + "JOIN populated_areas d ON c.populated_area_id = d.id "
                        + "JOIN regions e ON d.region_id = e.id "
                        + "JOIN countries f ON e.country_id = f.id "
                        + "WHERE c.name = ?;")) {

            statement.setString(1, municipalityName);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {

                    addresses.add(convertToAddress(rs));
                }

                return addresses;
            }
        }

    }

    void addAddress(String country, String region, String populatedAreaName, int populatedAreaId,
            String municipality, String postalCode, String street, String streetNo,
            Integer floor, Integer apartmentNo) throws SQLException {

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement statement = conn.prepareCall("{call insert_address(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setString(1, country);
            statement.setString(2, region);
            statement.setString(3, populatedAreaName);
            statement.setInt(4, populatedAreaId);
            statement.setString(5, municipality);
            statement.setString(6, postalCode);
            statement.setString(7, street);
            statement.setString(8, streetNo);
            statement.setInt(9, floor);
            statement.setInt(10, apartmentNo);

            statement.execute();
        }
    }

    private String convertToAddress(final ResultSet rs) throws SQLException {
        String result = String.format("%s %s Street", rs.getString("a.number"), rs.getString("b.name"));
        if (rs.getString("a.apartmentNo") != null) {
            result += ", Ap. " + rs.getString("a.apartmentNo");
        }
        result += String.format("%n%s, %s%n%s, %s",
                rs.getString("c.name"),
                rs.getString("d.name"),
                rs.getString("e.name"),
                rs.getString("f.name"));

        return result;
    }
}
