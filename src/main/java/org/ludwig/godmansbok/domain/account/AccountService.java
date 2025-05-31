package org.ludwig.godmansbok.domain.account;

import org.ludwig.godmansbok.domain.account.dto.AccountDTO;
import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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
}
