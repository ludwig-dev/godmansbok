package org.ludwig.godmansbok.domain.clients;

import org.ludwig.godmansbok.domain.account.Account;
import org.ludwig.godmansbok.domain.account.AccountRepository;
import org.ludwig.godmansbok.domain.clients.dto.ClientDTO;
import org.ludwig.godmansbok.domain.clients.dto.ClientUpdateDTO;
import org.ludwig.godmansbok.domain.godman.Godman;
import org.ludwig.godmansbok.domain.godman.GodmanRepository;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final GodmanRepository godmanRepository;
    private final AccountRepository accountRepository;

    public ClientService(ClientRepository clientRepository, GodmanRepository godmanRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.godmanRepository = godmanRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(Long godmanId, ClientDTO dto) {
        Godman godman = godmanRepository.findById(godmanId)
                .orElseThrow(() -> new ResourceNotFoundException("Godman not found"));

        Client c = new Client();
        c.setName(dto.name());
        c.setPersonalNumber(dto.personalNumber());
        c.setGodman(godman);
        Client savedClient = clientRepository.save(c);
        Account defaultAccount = new Account();
        defaultAccount.setAccountName("Transaktionskonto");
        defaultAccount.setAccountNumber("");
        defaultAccount.setClient(savedClient);
        accountRepository.save(defaultAccount);
        return savedClient;
    }

    public List<Client> getAllClientsByGodman(Long godmanId) {
        return clientRepository.findAllByGodmanId(godmanId);
    }

    public Client getClientById(Long godmanId, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        checkClientBelongsToGodman(client, godmanId);
        return client;
    }

    public void deleteClientById(Long godmanId, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        checkClientBelongsToGodman(client, godmanId);
        clientRepository.delete(client);
    }

    public Client updateClientById(Long godmanId, Long clientId, ClientUpdateDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        checkClientBelongsToGodman(client, godmanId);
        if (dto.getName() != null) {
            client.setName(dto.getName());
        }
        if (dto.getPersonalNumber() != null) {
            client.setPersonalNumber(dto.getPersonalNumber());
        }
        return clientRepository.save(client);
    }

    // Kontrollera att klienten faktiskt tillhör godmanId
    private void checkClientBelongsToGodman(Client client, Long godmanId) {
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }
    }

}
