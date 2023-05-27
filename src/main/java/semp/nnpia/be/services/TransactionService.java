package semp.nnpia.be.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semp.nnpia.be.domains.Transaction;
import semp.nnpia.be.repositories.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final BankAccountService bankAccountService;
    private final ITransactionRepository transactionRepository;

    public List<Transaction> getAccountTransactions(final Long accountId) throws ResourceNotFoundException {
        List<Transaction> transactions = new ArrayList<>();

        List<Transaction> transactionsRecipient = transactionRepository.findTransactionsByAccountRecipient_Id(accountId);
        List<Transaction> transactionsSender = transactionRepository.findTransactionsByAccountSender_Id(accountId);

        if (transactionsRecipient == null || transactionsSender == null) throw new ResourceNotFoundException();

        transactions.addAll(transactionsRecipient);
        transactions.addAll(transactionsSender);

        return transactions;
    }

    public Transaction createTransaction(final Transaction transaction) {
        var accountRecipient = transaction.getAccountRecipient();
        var accountSender = transaction.getAccountSender();
        var amount = transaction.getAmount();

        if (accountSender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in the sender's account.");
        }

        accountRecipient.setBalance(accountRecipient.getBalance() + amount);
        bankAccountService.saveBankAccount(accountRecipient);

        accountSender.setBalance(accountSender.getBalance() - amount);
        bankAccountService.saveBankAccount(accountSender);

        return transactionRepository.save(transaction);
    }
}