package org.ludwig.godmansbok.domain.transactions;

import org.ludwig.godmansbok.domain.account.Account;
import org.ludwig.godmansbok.domain.account.AccountRepository;
import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.domain.transactions.dto.TransactionDTO;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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

}
