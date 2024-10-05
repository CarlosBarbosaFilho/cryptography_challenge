package br.com.viaresults.cryptography.usecase;

import br.com.viaresults.cryptography.domain.TransactionDomain;
import br.com.viaresults.cryptography.infra.entity.Transaction;
import br.com.viaresults.cryptography.infra.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public void create(TransactionDomain domain) {

        domain.setCreditCardToken(encoder(domain.getCreditCardToken()));
        domain.setUserDocument(encoder(domain.getUserDocument()));
        domain.createTransaction(repository);

    }

    public List<TransactionDomain> transactions() {
       var transactions = TransactionDomain.builder().build().list(this.repository);
        return transactions.stream()
                .map(transaction -> TransactionDomain.builder()
                        .id(transaction.getId())
                        .creditCardToken(decoder(transaction.getCreditCardToken()))
                        .userDocument(decoder(transaction.getUserDocument()))
                        .value(transaction.getValue())
                        .build()).toList();
    }

    public String encoder(String value) {
        return createSecrete().encrypt(value);
    }

    public String decoder(String value) {
        return createSecrete().decrypt(value);
    }

    private StrongTextEncryptor createSecrete() {
        var secret = new StrongTextEncryptor();
        secret.setPassword("my-secret");
        return secret;
    }
}
