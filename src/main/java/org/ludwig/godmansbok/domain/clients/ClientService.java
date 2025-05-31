package org.ludwig.godmansbok.domain.clients;

import org.ludwig.godmansbok.domain.clients.dto.ClientDTO;
import org.ludwig.godmansbok.domain.godman.Godman;
import org.ludwig.godmansbok.domain.godman.GodmanRepository;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

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

        // Bygg Domain‐objekt från DTO
        Client c = new Client();
        c.setName(dto.name());
        c.setPersonalNumber(dto.personalNumber());
        c.setGodman(godman);

        return clientRepository.save(c);
    }
}
