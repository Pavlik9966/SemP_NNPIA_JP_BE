package semp.nnpia.be.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semp.nnpia.be.domains.BankAccount;
import semp.nnpia.be.repositories.IBankAccountRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BankAccountService {
    private final IBankAccountRepository bankAccountRepository;

    public List<BankAccount> getUserAccounts(final Long userId) throws ResourceNotFoundException {
        List<BankAccount> bankAccounts = bankAccountRepository.findBankAccountsByUserId(userId);

        if (bankAccounts == null) throw new ResourceNotFoundException();

        return bankAccounts;
    }

    public BankAccount getAccountByAccountNumber(final String accountNumber) throws ResourceNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findBankAccountByAccountNumber(accountNumber);

        if (bankAccount == null) throw new ResourceNotFoundException();

        return bankAccount;
    }

    public BankAccount getAccountById(final Long id) throws ResourceNotFoundException {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);

        if (bankAccount.isEmpty()) throw new ResourceNotFoundException();

        return bankAccount.get();
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}