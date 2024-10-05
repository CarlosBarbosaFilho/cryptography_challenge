package br.com.viaresults.cryptography.domain;

import br.com.viaresults.cryptography.infra.entity.Transaction;
import br.com.viaresults.cryptography.infra.repository.TransactionRepository;
import org.hibernate.sql.exec.spi.JdbcOperationQueryMutation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionDomainTest {

    @Mock
    private TransactionRepository repository;


    @Nested
    class CreateTransaction {

        @Test
        void shouldCreateTransactionWithValidParameters() {

            // arrange
            var domain = createTransactionDomain();

            var transaction = new Transaction(null, domain.getUserDocument(),
                    domain.getCreditCardToken(), domain.getValue());
            // act
            domain.createTransaction(repository);
            // assert
            verify(repository, times(1)).save(transaction);

        }
    }

    @Nested
    class List {

       @Test
        void shouldReturnAListTransactionDomain() {
           // arrange
           var domain = TransactionDomain.builder().build();

           var transactions = new ArrayList<Transaction>();
           transactions.add(new Transaction(1L,"03100721403", "0987654321", 200));

           var transactionsDomain = new ArrayList<TransactionDomain>();
           transactionsDomain.add(createTransactionDomain());

           when(repository.findAll()).thenReturn(transactions);

           // act
           var response = domain.list(repository);

           // assert
           verify(repository, times(1)).findAll();
           assertEquals(response, transactionsDomain );
           assertEquals(response.size(), 1);
       }

    }

    private TransactionDomain createTransactionDomain() {
        return  TransactionDomain
                .builder()
                .id(1L)
                .userDocument("03100721403")
                .creditCardToken("0987654321")
                .value(200)
                .build();
    }

}