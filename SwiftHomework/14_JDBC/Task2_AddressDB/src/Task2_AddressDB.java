
import java.sql.SQLException;

public class Task2_AddressDB {

    static final String DB_CONN_STRING = "jdbc:mysql://192.168.164.3:3306/addressDB";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "swift12345";

    public static void main(String[] args) throws SQLException {
        MySqlAddressData addressDataStorage = new MySqlAddressData(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);

        //testGetFullAddress(addressDataStorage);
        //testGetAddressByMuniName(addressDataStorage);
        //testAddAddress(addressDataStorage);
    }

    private static void testGetFullAddress(MySqlAddressData addressDataStorage) throws SQLException {
        System.out.println(addressDataStorage.getFullAddress(1));
        System.out.println();
        System.out.println(addressDataStorage.getFullAddress(3));
    }

    private static void testGetAddressByMuniName(MySqlAddressData addressDataStorage) throws SQLException {
        addressDataStorage.getAddresses("Serdika").forEach(address -> System.out.printf("%s%n%n", address));
    }

    private static void testAddAddress(MySqlAddressData addressDataStorage) throws SQLException {
        addressDataStorage.addAddress("Canada", "Yukon", "Dawson",
                2, "Downtown", "YT-21TY", "Church", "21", 2, 2);
    }

}
