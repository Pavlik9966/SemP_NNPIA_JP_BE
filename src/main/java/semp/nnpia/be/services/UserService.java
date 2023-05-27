package semp.nnpia.be.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semp.nnpia.be.domains.User;
import semp.nnpia.be.repositories.IUserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
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

    public User createUser(final User user) {
        return userRepository.save(user);
    }
}