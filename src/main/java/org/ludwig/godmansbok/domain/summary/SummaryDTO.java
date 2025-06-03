package org.ludwig.godmansbok.domain.summary;

import java.math.BigDecimal;

public record SummaryDTO(BigDecimal startBalance, BigDecimal sumIncome, BigDecimal totalAB, BigDecimal sumExpenses,
                         BigDecimal endBalance, BigDecimal totalCD, boolean match) {
}
