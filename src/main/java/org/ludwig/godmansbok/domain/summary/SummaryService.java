package org.ludwig.godmansbok.domain.summary;

import org.ludwig.godmansbok.domain.account.Account;
import org.ludwig.godmansbok.domain.account.AccountRepository;
import org.ludwig.godmansbok.domain.clients.Client;
import org.ludwig.godmansbok.domain.clients.ClientRepository;
import org.ludwig.godmansbok.domain.transactions.TransactionRepository;
import org.ludwig.godmansbok.exceptions.NotAuthorizedException;
import org.ludwig.godmansbok.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SummaryService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public SummaryService(ClientRepository clientRepository,
                          AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public SummaryDTO getFullSummaryForYear(Long godmanId, Long clientId, Integer year) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        if (!client.getGodman().getId().equals(godmanId)) {
            throw new NotAuthorizedException("Access denied");
        }
        Account account = accountRepository.findFirstByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        if (!account.getClient().getId().equals(clientId)) {
            throw new NotAuthorizedException("Access denied");
        }
        BigDecimal startBalance = account.getStartBalance() != null
                ? account.getStartBalance()
                : BigDecimal.ZERO;

        LocalDate fromDate = LocalDate.of(year, 1, 1);
        LocalDate toDate = LocalDate.of(year, 12, 31);
        BigDecimal sumIncome = transactionRepository
                .sumIncomesForAccountAndPeriod(account.getId(), fromDate, toDate);
        if (sumIncome == null) sumIncome = BigDecimal.ZERO;

        BigDecimal sumExpense = transactionRepository
                .sumExpensesForAccountAndPeriod(account.getId(), fromDate, toDate);
        if (sumExpense == null) sumExpense = BigDecimal.ZERO;

        BigDecimal endBalance = account.getEndBalance() != null
                ? account.getEndBalance()
                : BigDecimal.ZERO;

        BigDecimal totalAB = startBalance.add(sumIncome);
        BigDecimal totalCD = sumExpense.add(endBalance);
        boolean isMatch = totalAB.compareTo(totalCD) == 0;
        return new SummaryDTO(startBalance, sumIncome, totalAB, sumExpense, endBalance, totalCD, isMatch, account.getAccountName(), account.getAccountNumber(), account.getId());
    }

}
