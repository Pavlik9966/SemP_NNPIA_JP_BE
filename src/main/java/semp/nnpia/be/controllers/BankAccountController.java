package semp.nnpia.be.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import semp.nnpia.be.domains.Transaction;
import semp.nnpia.be.dtos.TransactionInputDto;
import semp.nnpia.be.services.BankAccountService;
import semp.nnpia.be.services.ResourceNotFoundException;
import semp.nnpia.be.services.TransactionService;
import semp.nnpia.be.services.UserService;

import java.util.stream.Collectors;

@RequestMapping("/account")
@RestController
public class BankAccountController {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getAccountTransactions(@PathVariable Long accountId) throws ResourceNotFoundException {
        logger.info("getAccountTransactions(" + accountId + ")");

        var result = transactionService.getAccountTransactions(accountId);

        return ResponseEntity.ok(result
                .stream()
                .map(Transaction::toDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping("/transaction/create")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> createTransaction(@Validated @RequestBody final TransactionInputDto dto) throws ResourceNotFoundException {
        logger.info("createTransaction(" + dto.getAmount() + ")");

        var result = transactionService.createTransaction(toEntity(dto));

        return ResponseEntity.ok(result.toDto());
    }

    private Transaction toEntity(final TransactionInputDto dto) throws ResourceNotFoundException {
        return new Transaction(
                userService.getUserById(dto.getSenderId()),
                bankAccountService.getAccountByAccountNumber(dto.getAccountNumberRecipient()),
                bankAccountService.getAccountById(dto.getAccountIdSender()),
                dto.getTransactionDate(),
                dto.getAmount(),
                dto.getCreatedAt(),
                dto.getDescription()
        );
    }
}