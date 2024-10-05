package br.com.viaresults.cryptography.controller;

import br.com.viaresults.cryptography.controller.request.RequestTransaction;
import br.com.viaresults.cryptography.controller.response.ResponseTransaction;
import br.com.viaresults.cryptography.usecase.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/create-transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody RequestTransaction requestTransaction) {
        this.service.create(requestTransaction.toDomain(requestTransaction));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/create-transaction")
    public ResponseEntity<List<ResponseTransaction>> list(){
        var transactions = this.service.transactions();
        var response = transactions.stream()
                .map(transaction -> ResponseTransaction.builder()
                        .id(transaction.getId())
                        .creditCardToken(transaction.getCreditCardToken())
                        .userDocument(transaction.getUserDocument())
                        .value(transaction.getValue()).build()).toList();

        return ResponseEntity.ok(response);
    }
}
