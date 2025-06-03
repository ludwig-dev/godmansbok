package org.ludwig.godmansbok.domain.account;

import org.ludwig.godmansbok.domain.account.dto.AccountDTO;
import org.ludwig.godmansbok.domain.account.dto.AccountUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/{clientId}/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
// SHOULD ONLY BE ONE ACCOUNT PER CLIENT
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public AccountDTO createAccount(
//            @PathVariable Long clientId,
//            @RequestBody AccountDTO dto,
//            @AuthenticationPrincipal UserDetails userDetails) {
//
//        Long godmanId = Long.parseLong(userDetails.getUsername());
//        return AccountDTO.toDto(accountService.createAccount(godmanId, clientId, dto));
//    }

    @GetMapping
    public List<AccountDTO> getAllAccounts(
            @PathVariable Long clientId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return AccountDTO.toDtos(accountService.getAllAccounts(godmanId, clientId));
    }

    @GetMapping("/{accountId}")
    public AccountDTO getAccountById(
            @PathVariable Long clientId,
            @PathVariable Long accountId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return AccountDTO.toDto(accountService.getAccountById(godmanId, clientId, accountId));
    }

    @PatchMapping("/{accountId}")
    public AccountDTO updateAccountById(
            @PathVariable Long clientId,
            @PathVariable Long accountId,
            @RequestBody AccountUpdateDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return AccountDTO.toDto(accountService.updateAccount(godmanId, clientId, accountId, dto));
    }

//    @DeleteMapping("/{accountId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAccountById(
//            @PathVariable Long clientId,
//            @PathVariable Long accountId,
//            @AuthenticationPrincipal UserDetails userDetails) {
//
//        Long godmanId = Long.parseLong(userDetails.getUsername());
//        accountService.deleteAccount(godmanId, clientId, accountId);
//    }

}
