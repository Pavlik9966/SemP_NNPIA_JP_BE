package semp.nnpia.be.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semp.nnpia.be.domains.BankAccount;
import semp.nnpia.be.services.BankAccountService;
import semp.nnpia.be.services.ResourceNotFoundException;
import semp.nnpia.be.services.UserService;

import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
public class UserController {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    UserService userService;
    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("/find")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) throws ResourceNotFoundException {
        logger.info("getUserByUsername(" + username + ")");

        var result = userService.getUserByUsername(username);

        return ResponseEntity.ok(result.toUserOutputDto());
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getUserAccounts(@RequestParam Long userId) throws ResourceNotFoundException {
        logger.info("getUserAccounts(" + userId + ")");

        var result = bankAccountService.getUserAccounts(userId);

        return ResponseEntity.ok(result
                .stream()
                .map(BankAccount::toDto)
                .collect(Collectors.toList())
        );
    }
}