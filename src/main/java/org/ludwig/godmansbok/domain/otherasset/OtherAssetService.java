package org.ludwig.godmansbok.domain.otherasset;

import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.domain.otherasset.dto.OtherAssetDTO;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherAssetService {

    private final OtherAssetRepository otherAssetRepository;
    private final ClientRepository clientRepository;

    public OtherAssetService(OtherAssetRepository otherAssetRepository,
                             ClientRepository clientRepository) {
        this.otherAssetRepository = otherAssetRepository;
        this.clientRepository = clientRepository;
    }

    public OtherAsset createOtherAsset(Long godmanId,
                                       Long clientId,
                                       OtherAssetDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        OtherAsset asset = new OtherAsset();
        asset.setAssetType(dto.assetType());
        asset.setDescription(dto.description());
        asset.setUnits(dto.units());
        asset.setValueStartOfYear(dto.valueStartOfYear());
        asset.setValueEndOfYear(dto.valueEndOfYear());
        asset.setAttachmentNumber(dto.attachmentNumber());
        asset.setClient(client);
        return otherAssetRepository.save(asset);
    }

    public List<OtherAsset> getAllOtherAssets(Long godmanId,
                                              Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        return otherAssetRepository.findAllByClientId(clientId);
    }
}
