package business;

import citizens.CitizenReposiotory;
import citizens.MySqlCitizenRepository;
import education.Education;
import education.EducationDegree;
import educations.EducationRepository;
import educations.MySqlEducationRepository;
import exceptions.DALException;
import insurance.SocialInsuranceRecord;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import personaldetails.Citizen;
import socialinsurance.MySqlSocialInsuranceRepository;
import socialinsurance.SocialInsuranceRepository;

public class BusinessLayer {

    private static final String DB_CONN_STRING = "jdbc:mysql://192.168.136.133:3306/citizen_registrations";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "swift12345";

    private static final CitizenReposiotory CITIZEN_REPO
            = new MySqlCitizenRepository( DB_CONN_STRING, DB_USERNAME, DB_PASSWORD );

    private static final EducationRepository EDUCATION_REPO
            = new MySqlEducationRepository( DB_CONN_STRING, DB_USERNAME, DB_PASSWORD );

    private static final SocialInsuranceRepository SOCIAL_INSURANCE_REPO
            = new MySqlSocialInsuranceRepository( DB_CONN_STRING, DB_USERNAME, DB_PASSWORD );

    static {

        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException ex ) {
            System.out.println( "[ERROR] JDBC Driver initialization failed." );
            System.out.println( ex );
        }
    }

    public static Citizen getCitizen(int citizenId) throws DALException {
        return CITIZEN_REPO.getCitizen( citizenId );
    }

    public static void addEducation(int citizenId, Education education) throws DALException {

        Citizen citizen = CITIZEN_REPO.getCitizen( citizenId );

        if ( citizen == null ) {
            return;
        }

        List<Education> educations = new ArrayList<>();
        educations.add( education );

        EDUCATION_REPO.insertEducations( citizenId, educations );
    }

    public static void addSocialInsuranceRecord(int citizenId, SocialInsuranceRecord record) throws DALException {

        Citizen citizen = CITIZEN_REPO.getCitizen( citizenId );

        if ( citizen == null ) {
            return;
        }

        List<SocialInsuranceRecord> records = new ArrayList<>();
        records.add( record );

        SOCIAL_INSURANCE_REPO.insertSocialInsuranceRecords( citizenId, records );
    }

    public static List<SocialInsuranceRecord> getSocialInsuranceRecords(int citizenId) throws DALException {
        List<SocialInsuranceRecord> records = SOCIAL_INSURANCE_REPO.getSocialInsuranceRecords( citizenId );

        sortSocialInsuranceRecordsByTime( records );

        return records;
    }

    public static BigDecimal calculateSocialBenefits(int citizenId) throws DALException {
        Citizen citizen = CITIZEN_REPO.getCitizen( citizenId );
        List<Education> educations = citizen.getEducations();

        //if citizen has secondary education
        if ( !educations.stream().anyMatch( x -> x.getDegree() == EducationDegree.Secondary && x.isGraduated() ) ) {
            return BigDecimal.ZERO;
        }

        List<SocialInsuranceRecord> records = SOCIAL_INSURANCE_REPO.getSocialInsuranceRecords( citizenId );

        if ( records.isEmpty() ) {
            return BigDecimal.ZERO;
        }

        sortSocialInsuranceRecordsByTime( records );

        SocialInsuranceRecord mostRecent = records.get( 0 );
        LocalDate now = LocalDate.now();

        //if it hasnt been 3 months
        if ( (now.getYear() - mostRecent.getYear()) * 12 + now.getMonthValue() - mostRecent.getMonth() < 3 ) {
            return BigDecimal.ZERO;
        }

        //calculate social benefit value
        int benefitsMonthStart = (now.getYear() - 2) * 12 + now.getMonthValue() - 3;

        BigDecimal sum = BigDecimal.ZERO;
        for ( SocialInsuranceRecord record : records ) {
            if ( record.getMonth() + record.getYear() * 12 >= benefitsMonthStart ) {
                sum = sum.add( record.getAmount() );
            }
        }

        return sum.divide( BigDecimal.valueOf( 24 ) );
    }

    private static void sortSocialInsuranceRecordsByTime(List<SocialInsuranceRecord> records) {
        records.sort( (SocialInsuranceRecord a, SocialInsuranceRecord b) -> {
            if ( b.getYear() == a.getYear() ) {
                return b.getMonth() - a.getMonth();
            }
            return b.getYear() - a.getYear();
        } );
    }
}
