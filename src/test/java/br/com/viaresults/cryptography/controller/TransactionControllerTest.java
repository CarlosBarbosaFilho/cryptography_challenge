package br.com.viaresults.cryptography.controller;

import br.com.viaresults.cryptography.controller.request.RequestTransaction;
import br.com.viaresults.cryptography.controller.response.ResponseTransaction;
import br.com.viaresults.cryptography.domain.TransactionDomain;
import br.com.viaresults.cryptography.infra.entity.Transaction;
import br.com.viaresults.cryptography.usecase.TransactionService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    TransactionService transactionService;

    @InjectMocks
    TransactionController transactionController;

    @Nested
    class Create {

        @Test
        void shouldCreateATransactionWithSuccess() {
            // arrange - prepare the mocks to execution
            var requestTransaction = createTransactionRequest();

            // act - execute method is tested
            var response = transactionController.create(requestTransaction);

            //assert - verify the execution
            assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
        }

        @Test
        void shouldReturnHttpStatusCodeCorrectly() {
            // arrange
            var response = TransactionDomain.builder()
                    .id(1L)
                    .value(200)
                    .creditCardToken("0987654321")
                    .userDocument("03100721403")
                    .build();

           var responseTransactions  = new ArrayList<>(List.of(response));

           when(transactionService.transactions()).thenReturn(responseTransactions);

            // act
            var result = transactionController.list();
            
            //assert
            assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
            assertEquals(Objects.requireNonNull(result.getBody()).size(), 1);

        }
    }

    private static RequestTransaction createTransactionRequest() {
        return RequestTransaction.builder()
                .value(200)
                .creditCardToken("0987654321234567890")
                .userDocument("03100721403")
                .build();
    }
}