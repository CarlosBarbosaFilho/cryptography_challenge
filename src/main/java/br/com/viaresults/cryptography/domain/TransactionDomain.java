package br.com.viaresults.cryptography.domain;

import br.com.viaresults.cryptography.infra.entity.Transaction;
import br.com.viaresults.cryptography.infra.repository.TransactionRepository;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionDomain {
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Integer value;


    public void createTransaction(TransactionRepository repository){
        repository.save(new Transaction(null, this.userDocument,this.creditCardToken, this.value));
    }

    public List<TransactionDomain> list(TransactionRepository repository) {
        return repository.findAll().stream()
                .map(transaction -> TransactionDomain
                        .builder()
                        .id(transaction.getId())
                        .userDocument(transaction.getUserDocument())
                        .creditCardToken(transaction.getCreditCardToken())
                        .value(transaction.getValue()).build()).toList();
    }

}
