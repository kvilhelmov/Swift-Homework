package address;

import exceptions.DALException;

public interface AddressRepository {

    Address getAddress(int id) throws DALException;

    int insertAddress(Address address) throws DALException;
}
