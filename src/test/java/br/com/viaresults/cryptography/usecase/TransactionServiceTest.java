package br.com.viaresults.cryptography.usecase;

import br.com.viaresults.cryptography.domain.TransactionDomain;
import br.com.viaresults.cryptography.infra.entity.Transaction;
import br.com.viaresults.cryptography.infra.repository.TransactionRepository;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {


    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private StrongTextEncryptor strongTextEncryptor;

    @Nested
    class Create {

        @Test
        void shouldCreateTransactionWithValidParameter() {
            // arrange
            var domain = createTransactionDomain();
            strongTextEncryptor.setPassword("my-secret");
            domain.setCreditCardToken(strongTextEncryptor.encrypt(domain.getCreditCardToken()));
            domain.setUserDocument(strongTextEncryptor.encrypt(domain.getUserDocument()));
            domain.createTransaction(transactionRepository);

            // act
            transactionService.create(domain);

            // assert
            assertNotEquals(createTransactionDomain().getCreditCardToken(), domain.getCreditCardToken());
            assertNotEquals(createTransactionDomain().getUserDocument(), domain.getUserDocument());
        }

//        @Test
//        void shouldListAllTransactions(){
//
//            // arrange
//            var transactions = new ArrayList<Transaction>();
//            transactions.add(new Transaction(1L,"03100721403", "0987654321", 200));
//
//            when(transactionRepository.findAll()).thenReturn(transactions);
//
//            // act
//            var response = transactionService.transactions();
//
//            // assert
//            verify(transactionRepository, times(1));
//            assertEquals(response.size(), 1);
//        }
    }


    private TransactionDomain createTransactionDomain(){
        return TransactionDomain.builder()
                .value(200)
                .creditCardToken("0987654321")
                .userDocument("03100721403")
                .build();
    }

}