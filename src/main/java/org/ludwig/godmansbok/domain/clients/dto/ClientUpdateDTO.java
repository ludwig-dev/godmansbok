package org.ludwig.godmansbok.domain.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateDTO {
    private String name;
    private String personalNumber;
}
