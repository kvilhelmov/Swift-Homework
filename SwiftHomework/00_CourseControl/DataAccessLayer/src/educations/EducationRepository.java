package educations;

import education.Education;
import exceptions.DALException;
import java.util.List;

public interface EducationRepository {

    List<Integer> insertEducations(int citizenId, List<Education> educations) throws DALException;

    List<Education> getEducations(int citizenId) throws DALException;
}
