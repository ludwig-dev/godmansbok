package org.ludwig.godmansbok.domain.account;

import org.ludwig.godmansbok.domain.account.dto.AccountDTO;
import org.ludwig.godmansbok.domain.account.dto.AccountUpdateDTO;
import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public Account createAccount(Long godmanId, Long clientId, AccountDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        // Kontrollera att klienten tillhör rätt godman
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = new Account();
        account.setAccountName(dto.accountName());
        account.setAccountNumber(dto.accountNumber());
        account.setClient(client);

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(Long godmanId, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        return accountRepository.findAllByClientId(clientId);
    }

    public Account getAccountById(Long godmanId, Long clientId, Long accountId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        return account;
    }

    public Account updateAccount(Long godmanId,
                                 Long clientId,
                                 Long accountId,
                                 AccountUpdateDTO dto) {
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

        if (dto.getAccountName() != null) {
            account.setAccountName(dto.getAccountName());
        }
        if (dto.getAccountNumber() != null) {
            account.setAccountNumber(dto.getAccountNumber());
        }

        return accountRepository.save(account);
    }

    public void deleteAccount(Long godmanId, Long clientId, Long accountId) {
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

        accountRepository.delete(account);
    }
}
