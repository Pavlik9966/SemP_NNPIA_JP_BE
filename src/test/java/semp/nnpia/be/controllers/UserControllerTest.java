package semp.nnpia.be.controllers;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import semp.nnpia.be.domains.Address;
import semp.nnpia.be.domains.State;
import semp.nnpia.be.domains.User;
import semp.nnpia.be.repositories.IAddressRepository;
import semp.nnpia.be.repositories.IStateRepository;
import semp.nnpia.be.repositories.IUserRepository;
import semp.nnpia.be.services.UserService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private IStateRepository stateRepository;
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
    private User createdUser = null;
    private CloseableHttpClient client;
    private HttpHost host;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        stateRepository.save(state);
        addressRepository.save(address);
        createdUser = userService.createUser(user);
        client = HttpClientBuilder.create().build();
        host = new HttpHost("localhost", port);
    }

    @AfterEach
    public void tearDown() throws IOException {
        userRepository.deleteAll();
        addressRepository.deleteAll();
        stateRepository.deleteAll();
        client.close();
    }

    @Test
    public void validUserEndpoint() throws IOException, URISyntaxException {
        String baseUrl = "/api/v1/user/find";

        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.addParameter("username", createdUser.getUsername());
        URI uri = uriBuilder.build();

        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(host, request);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void invalidUserEndpoint() throws IOException, URISyntaxException {
        String baseUrl = "/api/v1/user/find";

        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.addParameter("username", "username");
        URI uri = uriBuilder.build();

        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(host, request);

        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusLine().getStatusCode());
    }
}