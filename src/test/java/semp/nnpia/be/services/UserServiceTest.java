package semp.nnpia.be.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semp.nnpia.be.domains.Address;
import semp.nnpia.be.domains.State;
import semp.nnpia.be.domains.User;
import semp.nnpia.be.repositories.IAddressRepository;
import semp.nnpia.be.repositories.IStateRepository;
import semp.nnpia.be.repositories.IUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private IStateRepository stateRepository;
    @Autowired
    private StateService stateService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserService userService;
    private final State state = new State("Czech Republic", "CZ");
    private final Address address = new Address("Vodičkova ulice 123", "Prague", "110 00", state);
    private final User user = new User(
            "janko123",
            "heslo456",
            "Jan",
            "Novák",
            LocalDate.of(1990, 5, 15),
            "+420123456789",
            "jan.novak@example.com",
            address,
            LocalDateTime.now()
    );
    private State createdState = null;
    private Address createdAddress = null;
    private User createdUser = null;

    @BeforeEach
    public void setUp() {
        createdState = stateService.createState(state);
        createdAddress = addressService.createAddress(address);
        createdUser = userService.createUser(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        addressRepository.deleteAll();
        stateRepository.deleteAll();
    }

    @Test
    public void checkAddress() throws ResourceNotFoundException {
        State actualState = stateService.getStateById(createdState.getId());
        Address actualAddress = addressService.getAddressById(createdAddress.getId());

        assertEquals(actualAddress.getState(), actualState);
    }

    @Test
    public void findUserById() throws ResourceNotFoundException {
        User actual = userService.getUserById(createdUser.getId());

        assertEquals(user.getUsername(), actual.getUsername());
        assertEquals(user.getPassword(), actual.getPassword());
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getSurname(), actual.getSurname());
        assertEquals(user.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals(user.getPhone(), actual.getPhone());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getAddress(), actual.getAddress());
        assertEquals(user.getCreatedAt().withNano(0), actual.getCreatedAt().withNano(0));

        /*assertEquals(user, actual);*/
    }
}