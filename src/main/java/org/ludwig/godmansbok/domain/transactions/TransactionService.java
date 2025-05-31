package org.ludwig.godmansbok.domain.transactions;

import org.ludwig.godmansbok.domain.account.Account;
import org.ludwig.godmansbok.domain.account.AccountRepository;
import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.domain.transactions.dto.TransactionDTO;
import org.ludwig.godmansbok.domain.transactions.dto.TransactionUpdateDTO;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public Transaction createTransaction(Long godmanId,
                                         Long clientId,
                                         Long accountId,
                                         TransactionDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        if (!account.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Transaction transaction = new Transaction();
        transaction.setDate(dto.date());
        transaction.setAmount(dto.amount());
        transaction.setType(dto.type());
        transaction.setDescription(dto.description());
        transaction.setAttachmentNumber(dto.attachmentNumber());
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(Long godmanId,
                                                Long clientId,
                                                Long accountId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        if (!account.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }

        return transactionRepository.findAllByAccountId(accountId);
    }

    public Transaction getTransactionById(Long godmanId,
                                          Long clientId,
                                          Long accountId,
                                          Long transactionId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        if (!account.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Transaction tx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (!tx.getAccount().getId().equals(accountId)) {
            throw new NotAuthorizedException("Access denied");
        }

        return tx;
    }

    public Transaction updateTransaction(Long godmanId,
                                         Long clientId,
                                         Long accountId,
                                         Long transactionId,
                                         TransactionUpdateDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        if (!account.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Transaction tx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        if (!tx.getAccount().getId().equals(accountId)) {
            throw new NotAuthorizedException("Access denied");
        }

        if (dto.getDate() != null) {
            tx.setDate(dto.getDate());
        }
        if (dto.getAmount() != null) {
            tx.setAmount(dto.getAmount());
        }
        if (dto.getType() != null) {
            tx.setType(dto.getType());
        }
        if (dto.getDescription() != null) {
            tx.setDescription(dto.getDescription());
        }
        if (dto.getAttachmentNumber() != null) {
            tx.setAttachmentNumber(dto.getAttachmentNumber());
        }

        return transactionRepository.save(tx);
    }

    public void deleteTransaction(Long godmanId,
                                  Long clientId,
                                  Long accountId,
                                  Long transactionId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        if (!account.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Transaction tx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        if (!tx.getAccount().getId().equals(accountId)) {
            throw new NotAuthorizedException("Access denied");
        }

        transactionRepository.delete(tx);
    }

}
