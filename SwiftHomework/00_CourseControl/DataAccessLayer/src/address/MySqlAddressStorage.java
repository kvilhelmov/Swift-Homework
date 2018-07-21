package address;

import exceptions.DALException;
import java.sql.*;

public class MySqlAddressStorage implements AddressRepository {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

   
    public MySqlAddressStorage(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;

    }

    @Override
    public Address getAddress(int id) throws DALException {

        try (Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                PreparedStatement statement = conn.prepareStatement( "SELECT * FROM addresses WHERE id = ?" ) ) {

            statement.setInt( 1, id );

            try ( ResultSet rs = statement.executeQuery() ) {

                if ( !rs.next() ) {
                    return null;
                }

                Integer floor = rs.getInt( "floor" );
                if ( rs.wasNull() ) {
                    floor = null;
                }

                Integer apartmentNo = rs.getInt( "apartmentNo" );
                if ( rs.wasNull() ) {
                    apartmentNo = null;
                }

                Address address = new Address( rs.getString( "country" ),
                        rs.getString( "city" ),
                        rs.getString( "municipality" ),
                        rs.getString( "postal_code" ),
                        rs.getString( "street" ),
                        rs.getString( "number" ),
                        floor,
                        apartmentNo );

                return address;
            }
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while retrieving address with id: " + id, ex );
        }
    }

    @Override
    public int insertAddress(Address address) throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                CallableStatement statement = conn.prepareCall( "{call insert_address(?, ?, ?, ?, ?, ?, ?, ?, ?)}" ) ) {

            statement.setString( "country", address.getCountry() );
            statement.setString( "city", address.getCity() );
            statement.setString( "municipality", address.getMunicipality() );
            statement.setString( "postal_code", address.getPostalCode() );
            statement.setString( "street", address.getStreet() );
            statement.setString( "streetNo", address.getNumber() );
            if ( address.getFloor() == null ) {
                statement.setNull( "floor", Types.INTEGER );
            } else {
                statement.setInt( "floor", address.getFloor() );
            }
            if ( address.getApartmentNo() == null ) {
                statement.setNull( "apartmentNo", Types.INTEGER );
            } else {
                statement.setInt( "apartmentNo", address.getApartmentNo() );
            }

            statement.registerOutParameter( "id", Types.INTEGER );

            statement.executeQuery();

            return statement.getInt( "id" );
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while inserting an address. Address: " + address, ex );
        }
    }

    public void deleteAll() throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                Statement statement = conn.createStatement() ) {

            statement.execute( "SET FOREIGN_KEY_CHECKS = 0;" );
            statement.execute( "TRUNCATE TABLE addresses;" );
            statement.execute( "SET FOREIGN_KEY_CHECKS = 1;" );
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while deleting data from table addresses.", ex );
        }
    }
}
