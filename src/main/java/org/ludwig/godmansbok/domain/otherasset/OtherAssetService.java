package org.ludwig.godmansbok.domain.otherasset;

import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.domain.otherasset.dto.OtherAssetDTO;
import org.ludwig.godmansbok.domain.otherasset.dto.OtherAssetUpdateDTO;
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

    public OtherAsset getOtherAssetById(Long godmanId,
                                        Long clientId,
                                        Long otherAssetId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        OtherAsset asset = otherAssetRepository.findById(otherAssetId)
                .orElseThrow(() -> new ResourceNotFoundException("OtherAsset not found"));

        if (!asset.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }
        return asset;
    }

    public OtherAsset updateOtherAsset(Long godmanId,
                                       Long clientId,
                                       Long otherAssetId,
                                       OtherAssetUpdateDTO dto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }

        OtherAsset asset = otherAssetRepository.findById(otherAssetId)
                .orElseThrow(() -> new ResourceNotFoundException("OtherAsset not found"));

        if (!asset.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }

        if (dto.getAssetType() != null) {
            asset.setAssetType(dto.getAssetType());
        }
        if (dto.getDescription() != null) {
            asset.setDescription(dto.getDescription());
        }
        if (dto.getUnits() != null) {
            asset.setUnits(dto.getUnits());
        }
        if (dto.getValueStartOfYear() != null) {
            asset.setValueStartOfYear(dto.getValueStartOfYear());
        }
        if (dto.getValueEndOfYear() != null) {
            asset.setValueEndOfYear(dto.getValueEndOfYear());
        }
        if (dto.getAttachmentNumber() != null) {
            asset.setAttachmentNumber(dto.getAttachmentNumber());
        }

        return otherAssetRepository.save(asset);
    }
}
