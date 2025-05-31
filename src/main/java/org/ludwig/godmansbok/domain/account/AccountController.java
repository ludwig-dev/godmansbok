package org.ludwig.godmansbok.domain.account;

import org.ludwig.godmansbok.domain.account.dto.AccountDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients/{clientId}/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(
            @PathVariable Long clientId,
            @RequestBody AccountDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return AccountDTO.toDto(accountService.createAccount(godmanId, clientId, dto));
    }
}
