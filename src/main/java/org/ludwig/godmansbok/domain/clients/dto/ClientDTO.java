package org.ludwig.godmansbok.domain.clients.dto;

import org.ludwig.godmansbok.domain.clients.Client;

import java.util.List;

public record ClientDTO(String name, String personalNumber) {
    public static ClientDTO toDto(Client client) {
        return new ClientDTO(client.getName(), client.getPersonalNumber());
    }

    public static List<ClientDTO> toDtos(List<Client> clients) {
        return clients.stream().map(ClientDTO::toDto).toList();
    }
}
