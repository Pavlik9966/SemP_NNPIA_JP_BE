package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import semp.nnpia.be.domains.BankAccount;

import java.util.List;

public interface IBankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findBankAccountsByUserId(Long id);

    @Query("select ba from BankAccount ba where ba.accountNumber = :accountNumber")
    BankAccount findBankAccountByAccountNumber(String accountNumber);
}