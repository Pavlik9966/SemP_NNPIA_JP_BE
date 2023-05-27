package semp.nnpia.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semp.nnpia.be.domains.Transaction;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsByAccountRecipient_Id(Long id);

    List<Transaction> findTransactionsByAccountSender_Id(Long id);
}