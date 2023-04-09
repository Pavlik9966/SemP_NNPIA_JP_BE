package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semp.nnpia.be.domains.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}