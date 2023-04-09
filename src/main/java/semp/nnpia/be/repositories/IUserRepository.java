package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import semp.nnpia.be.domains.Role;
import semp.nnpia.be.domains.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);

    @Query("select u from User u join u.roles r where r = :role")
    List<User> getUsersByRole(Role role);
}