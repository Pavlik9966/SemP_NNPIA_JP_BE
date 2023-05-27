package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semp.nnpia.be.domains.BankAccount;

import java.util.List;

public interface IBankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findBankAccountsByUserId(Long id);

    BankAccount findBankAccountByAccountNumber(String accountNumber);
}