package org.ludwig.godmansbok.domain.liability;

import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.domain.liability.dto.LiabilityDTO;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiabilityService {
    private final LiabilityRepository liabilityRepository;
    private final ClientRepository clientRepository;

    public LiabilityService(LiabilityRepository liabilityRepository,
                            ClientRepository clientRepository) {
        this.liabilityRepository = liabilityRepository;
        this.clientRepository = clientRepository;
    }

    public Liability createLiability(Long godmanId,
                                     Long clientId,
                                     LiabilityDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Liability liability = new Liability();
        liability.setCreditor(dto.creditor());
        liability.setDebtStartOfYear(dto.debtStartOfYear());
        liability.setDebtEndOfYear(dto.debtEndOfYear());
        liability.setChangeAmount(dto.changeAmount());
        liability.setAttachmentNumber(dto.attachmentNumber());
        liability.setClient(client);
        return liabilityRepository.save(liability);
    }

    public List<Liability> getAllLiabilities(Long godmanId, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }
        return liabilityRepository.findAllByClientId(clientId);
    }

    public Liability getLiabilityById(Long godmanId,
                                      Long clientId,
                                      Long liabilityId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        Liability liability = liabilityRepository.findById(liabilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Liability not found"));

        if (!liability.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }
        return liability;
    }
}
