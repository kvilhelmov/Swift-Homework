package citizens;

import exceptions.DALException;
import personaldetails.Citizen;

public interface CitizenReposiotory {

    public Citizen getCitizen(int id) throws DALException;

    public int insertCitizen(Citizen citizen) throws DALException;
}
