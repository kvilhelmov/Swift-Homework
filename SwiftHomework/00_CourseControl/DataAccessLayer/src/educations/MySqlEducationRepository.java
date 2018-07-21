package educations;

import exceptions.DALException;
import education.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlEducationRepository implements EducationRepository {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

    public MySqlEducationRepository(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;

    }

    @Override
    public List<Integer> insertEducations(int citizenId, List<Education> educations) throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                CallableStatement statement = conn.prepareCall( "{call insert_education(?, ?, ?, ?, ?, ?, ?, ?)}" ) ) {

            List<Integer> ids = new ArrayList<>();

            for ( Education education : educations ) {
                statement.setInt( "citizen_id", citizenId );
                statement.setInt( "type_id", education.getDegree().getValue() );
                statement.setString( "institution_name", education.getInstitutionName() );
                statement.setDate( "enrollment_date", Date.valueOf( education.getEnrollmentDate() ) );
                statement.setDate( "graduation_date", Date.valueOf( education.getGraduationDate() ) );
                statement.setBoolean( "graduated", education.isGraduated() );
                if ( education instanceof GradedEducation && education.isGraduated() ) {
                    statement.setDouble( "final_grade", ((GradedEducation) education).getFinalGrade() );
                } else {
                    statement.setObject( "final_grade", null );
                }
                statement.registerOutParameter( "id", Types.INTEGER );
                statement.executeQuery();

                ids.add( statement.getInt( "id" ) );
            }

            return ids;

        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while inserting educations.", ex );
        }
    }

    @Override
    public List<Education> getEducations(int citizenId) throws DALException {

        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * FROM educations "
                        + "INNER JOIN education_types "
                        + "ON type_id = education_types.id "
                        + "WHERE citizen_id = ? "
                        + "ORDER BY graduation_date DESC" ) ) {

            statement.setInt( 1, citizenId );

            ResultSet rs = statement.executeQuery();

            List<Education> educations = new ArrayList<>();
            while ( rs.next() ) {

                EducationDegree degree = Enum.valueOf( EducationDegree.class, rs.getString( "education_types.name" ) );
                if ( null == degree ) {
                    throw new DALException( "Unknown degree: " + degree );
                }

                Education education;

                String institutionName = rs.getString( "institution_name" );
                LocalDate enrollmentDate = rs.getDate( "enrollment_date" ).toLocalDate();
                LocalDate graduationDate = rs.getDate( "graduation_date" ).toLocalDate();
                Integer finalGrade = rs.getInt( "final_grade" );
                if ( rs.wasNull() ) {
                    finalGrade = null;
                }

                switch ( degree ) {
                    case Primary:
                        education = new PrimaryEducation( institutionName, enrollmentDate, graduationDate );
                        break;
                    case Secondary:
                        education = new SecondaryEducation( institutionName, enrollmentDate, graduationDate );
                        if ( finalGrade != null ) {
                            ((GradedEducation) education).gotGraduated( finalGrade );
                        }
                        break;
                    case Bachelor:
                    case Master:
                    case Doctorate:
                        education = new HigherEducation( institutionName, enrollmentDate, graduationDate, degree );
                        if ( finalGrade != null ) {
                            ((GradedEducation) education).gotGraduated( finalGrade );
                        }
                        break;

                    default:
                        throw new DALException( "Unknown degree: " + degree );
                }

                educations.add( education );
            }

            return educations;

        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while retrieving educations.", ex );
        }
    }

    public void deleteAll() throws DALException {
        try ( Connection conn = DriverManager.getConnection( _dbConnectionString, _dbUsername, _dbPassword );
                Statement statement = conn.createStatement() ) {

            statement.execute( "SET FOREIGN_KEY_CHECKS = 0;" );
            statement.execute( "TRUNCATE TABLE educations;" );
            statement.execute( "SET FOREIGN_KEY_CHECKS = 1;" );
        } catch ( SQLException ex ) {
            throw new DALException( "ERROR while deleting data from table addresses.", ex );
        }
    }
}
