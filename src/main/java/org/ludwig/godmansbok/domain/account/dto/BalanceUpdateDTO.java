package org.ludwig.godmansbok.domain.account.dto;

import java.math.BigDecimal;

public record BalanceUpdateDTO(BigDecimal startBalance, BigDecimal endBalance) {
}
