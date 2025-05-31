package org.ludwig.godmansbok.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateDTO {
    private String accountName;
    private String accountNumber;
}
