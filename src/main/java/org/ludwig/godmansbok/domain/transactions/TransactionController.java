package org.ludwig.godmansbok.domain.transactions;

import jakarta.validation.Valid;
import org.ludwig.godmansbok.domain.transactions.dto.TransactionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/{clientId}/accounts/{accountId}/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO createTransaction(
            @PathVariable Long clientId,
            @PathVariable Long accountId,
            @Valid @RequestBody TransactionDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return TransactionDTO.toDto(transactionService.createTransaction(godmanId, clientId, accountId, dto));
    }

    @GetMapping
    public List<TransactionDTO> getAllTransactions(
            @PathVariable Long clientId,
            @PathVariable Long accountId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long godmanId = Long.parseLong(userDetails.getUsername());
        return TransactionDTO.toDtos(transactionService.getAllTransactions(godmanId, clientId, accountId));
    }
}
