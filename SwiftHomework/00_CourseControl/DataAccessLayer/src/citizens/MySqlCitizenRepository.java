package citizens;

import address.AddressRepository;
import exceptions.DALException;
import address.MySqlAddressStorage;
import education.Education;
import educations.MySqlEducationRepository;
import java.sql.*;
import personaldetails.*;
import educations.EducationRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import socialinsurance.MySqlSocialInsuranceRepository;
import socialinsurance.SocialInsuranceRepository;

public class MySqlCitizenRepository implements CitizenReposiotory {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

    public final AddressRepository _mySqlAddressStorage;
    public final EducationRepository _mySqlEducationStorage;
    public final SocialInsuranceRepository _mySqlSocialInsuranceStorage;

    public MySqlCitizenRepository(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;

        _mySqlAddressStorage = new MySqlAddressStorage( dbConnectionString, dbUsername, dbPassword );
        _mySqlEducationStorage = new MySqlEducationRepository( dbConnectionString, dbUsername, dbPassword );
        _mySqlSocialInsuranceStorage = new MySqlSocialInsuranceRepository( dbConnectionString, dbUsername, dbPassword );
    }

    @Override
    public Citizen getCitizen(int id) throws DALException {

        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                PreparedStatement statement = conn.prepareStatement( "SELECT * FROM citizens WHERE citizens.id = ?" ) ) {

            statement.setInt( 1, id );

            try ( ResultSet rs = statement.executeQuery() ) {

                if ( !rs.next() ) {
                    return null;
                }

                String genderString = rs.getString( "gender" );
                Gender gender;

                if ( genderString.equalsIgnoreCase( "m" ) ) {
                    gender = Gender.Male;
                } else if ( genderString.equalsIgnoreCase( "f" ) ) {
                    gender = Gender.Female;
                } else {
                    throw new DALException( "Unknown gender: " + genderString );
                }

                Citizen citizen = new Citizen(
                        rs.getString( "first_name" ),
                        rs.getString( "middle_name" ),
                        rs.getString( "last_name" ),
                        gender,
                        rs.getInt( "height" ),
                        rs.getDate( "birth_date" ).toLocalDate()
                );

                citizen.setAddress( _mySqlAddressStorage.getAddress( rs.getInt( "address_id" ) ) );

                for ( Education education : _mySqlEducationStorage.getEducations( id ) ) {
                    citizen.addEducation( education );
                }

                return citizen;
            }
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while retrieving citizen with id: " + id, ex );
        }
    }

    @Override
    public int insertCitizen(Citizen citizen) throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                CallableStatement statement = conn.prepareCall( "{call insert_citizen(?, ?, ?, ?, ?, ?, ?, ?)}" ) ) {

            // would be better if insert_citizen stored procedure calls 
            // insert_address internally instead of calling insert_address from 
            // the code, however this is what we choose for building this demo
            int addressId = _mySqlAddressStorage.insertAddress( citizen.getAddress() );
            statement.setString( "first_name", citizen.getFirstName() );
            statement.setString( "middle_name", citizen.getMiddleName() );
            statement.setString( "last_name", citizen.getLastName() );
            statement.setString( "gender", citizen.getGender().toString().substring( 0, 1 ) );
            statement.setInt( "height", citizen.getHeight() );
            statement.setDate( "birth_date", Date.valueOf( citizen.getDateOfBirth() ) );
            statement.setInt( "address_id", addressId );

            statement.registerOutParameter( "id", Types.INTEGER );

            statement.executeQuery();
            int id = statement.getInt( "id" );

            // same note - better have insert_citizen stored procedure call insert_educations and
            // insert_social_records
            _mySqlEducationStorage.insertEducations( id, citizen.getEducations() );
            _mySqlSocialInsuranceStorage.insertSocialInsuranceRecords( id, citizen.getSocialInsuranceRecords() );

            return id;

        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while inserting a new citizen: " + citizen, ex );
        }
    }

    public void deleteAll() throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                Statement statement = conn.createStatement() ) {

            statement.execute( "SET FOREIGN_KEY_CHECKS = 0;" );
            statement.execute( "TRUNCATE TABLE citizens;" );
            statement.execute( "SET FOREIGN_KEY_CHECKS = 1;" );
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while deleting data from table addresses.", ex );
        }
    }
}
