package org.ludwig.godmansbok.domain.clients;

import org.ludwig.godmansbok.domain.clients.dto.ClientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody ClientDTO dto,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        Long godmanId = Long.parseLong(userDetails.getUsername());
        return ClientDTO.toDto(clientService.createClient(godmanId, dto));
    }

    @GetMapping
    public List<ClientDTO> getAllClients(@AuthenticationPrincipal UserDetails userDetails) {
        Long godmanId = Long.parseLong(userDetails.getUsername());
        return ClientDTO.toDtos(clientService.getAllClientsByGodman(godmanId));
    }

    @GetMapping("/{clientId}")
    public ClientDTO getClientById(@PathVariable Long clientId,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        Long godmanId = Long.parseLong(userDetails.getUsername());
        return ClientDTO.toDto(clientService.getClientById(godmanId, clientId));
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable Long clientId,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        Long godmanId = Long.parseLong(userDetails.getUsername());
        clientService.deleteClientById(godmanId, clientId);
    }
}
