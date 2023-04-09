package semp.nnpia.be.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semp.nnpia.be.domains.Role;
import semp.nnpia.be.domains.User;
import semp.nnpia.be.repositories.IRoleRepository;
import semp.nnpia.be.repositories.IUserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;

    public User getUserById(final Long id) throws ResourceNotFoundException {
        Optional<User> result = userRepository.findById(id);

        if (result.isEmpty()) throw new ResourceNotFoundException();

        return result.get();
    }

    public User getUserByUsername(final String username) throws ResourceNotFoundException {
        User result = userRepository.getUserByUsername(username);

        if (result == null) throw new ResourceNotFoundException();

        return result;
    }

    public List<User> getUsersByRole(final String roleName) throws ResourceNotFoundException {
        Role roleResult = roleRepository.findRoleByName(roleName);

        if (roleResult == null) throw new ResourceNotFoundException();

        return userRepository.getUsersByRole(roleResult);
    }

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }
}