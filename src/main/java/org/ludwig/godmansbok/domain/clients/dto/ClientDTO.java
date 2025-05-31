package org.ludwig.godmansbok.domain.clients.dto;

import org.ludwig.godmansbok.domain.clients.Client;

public record ClientDTO(String name, String personalNumber) {
    public static ClientDTO toDto(Client client) {
        return new ClientDTO(client.getName(), client.getPersonalNumber());
    }
}
