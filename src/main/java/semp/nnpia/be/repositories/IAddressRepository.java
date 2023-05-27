package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semp.nnpia.be.domains.Address;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}