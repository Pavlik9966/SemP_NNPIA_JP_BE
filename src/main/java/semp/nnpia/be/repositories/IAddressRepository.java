package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semp.nnpia.be.domains.Address;
import semp.nnpia.be.domains.State;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByStreetAddressAndCityAndZipCodeAndState(String streetAddress, String city, String zipCode, State state);
}