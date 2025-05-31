package org.ludwig.godmansbok.domain.clients;

import org.ludwig.godmansbok.domain.clients.dto.ClientDTO;
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

    public ClientService(ClientRepository clientRepository, GodmanRepository godmanRepository) {
        this.clientRepository = clientRepository;
        this.godmanRepository = godmanRepository;
    }

    public Client createClient(Long godmanId, ClientDTO dto) {
        Godman godman = godmanRepository.findById(godmanId)
                .orElseThrow(() -> new ResourceNotFoundException("Godman not found"));

        Client c = new Client();
        c.setName(dto.name());
        c.setPersonalNumber(dto.personalNumber());
        c.setGodman(godman);

        return clientRepository.save(c);
    }

    public List<Client> getAllClientsByGodman(Long godmanId) {
        return clientRepository.findAllByGodmanId(godmanId);
    }

    public Client getClientById(Long godmanId, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        // Kontrollera att klienten faktiskt tillhör godmanId
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }
        return client;
    }
}
