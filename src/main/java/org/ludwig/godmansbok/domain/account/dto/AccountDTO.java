package org.ludwig.godmansbok.domain.account.dto;

import org.ludwig.godmansbok.domain.account.Account;

import java.util.List;

public record AccountDTO(Long id, String accountName, String accountNumber) {
    public static AccountDTO toDto(Account account) {
        return new AccountDTO(account.getId(), account.getAccountName(), account.getAccountNumber());
    }

    public static List<AccountDTO> toDtos(List<Account> accounts) {
        return accounts.stream().map(AccountDTO::toDto).toList();
    }
}
