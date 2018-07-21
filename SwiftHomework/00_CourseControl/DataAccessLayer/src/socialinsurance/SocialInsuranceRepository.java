package socialinsurance;

import exceptions.DALException;
import insurance.SocialInsuranceRecord;
import java.util.List;

public interface SocialInsuranceRepository {

    public List<SocialInsuranceRecord> getSocialInsuranceRecords(int citizenId) throws DALException;

    public List<Integer> insertSocialInsuranceRecords(int citizenId, List<SocialInsuranceRecord> records) throws DALException;
}
