package socialinsurance;

import exceptions.DALException;
import insurance.SocialInsuranceRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlSocialInsuranceRepository implements SocialInsuranceRepository {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

    public MySqlSocialInsuranceRepository(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;
    }

    @Override
    public List<SocialInsuranceRecord> getSocialInsuranceRecords(int citizenId) throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                PreparedStatement statement = conn.prepareStatement( "SELECT * FROM social_insurances WHERE citizen_id = ?" ) ) {

            List<SocialInsuranceRecord> records = new ArrayList<>();

            statement.setInt( 1, citizenId );

            try ( ResultSet rs = statement.executeQuery() ) {
                while ( rs.next() ) {
                    records.add( new SocialInsuranceRecord( rs.getInt( "year" ), rs.getInt( "month" ), rs.getBigDecimal( "amount" ) ) );
                }
            }

            return records;

        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while retrieving social insurance records.", ex );
        }
    }

    @Override
    public List<Integer> insertSocialInsuranceRecords(int citizenId, List<SocialInsuranceRecord> records) throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                CallableStatement statement = conn.prepareCall( "{call insert_social_insurance_record(?, ?, ?, ?, ?)}" ) ) {

            List<Integer> ids = new ArrayList<>();

            for ( SocialInsuranceRecord record : records ) {

                statement.setInt( "citizen_id", citizenId );
                statement.setInt( "year", record.getYear() );
                statement.setInt( "month", record.getMonth() );
                statement.setBigDecimal( "amount", record.getAmount() );

                statement.registerOutParameter( "id", Types.INTEGER );

                statement.executeQuery();
                ids.add( statement.getInt( "id" ) );
            }

            return ids;

        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while inserting social insurance records.", ex );
        }
    }

    public void deleteAll() throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                Statement statement = conn.createStatement() ) {

            statement.execute( "SET FOREIGN_KEY_CHECKS = 0;" );
            statement.execute( "TRUNCATE TABLE social_insurances;" );
            statement.execute( "SET FOREIGN_KEY_CHECKS = 1;" );
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while deleting data from table addresses.", ex );
        }
    }
}
