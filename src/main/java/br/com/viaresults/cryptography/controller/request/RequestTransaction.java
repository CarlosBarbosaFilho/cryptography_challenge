package br.com.viaresults.cryptography.controller.request;

import br.com.viaresults.cryptography.domain.TransactionDomain;
import lombok.*;


@Getter
@Builder
public class RequestTransaction {
    private String userDocument;
    private String creditCardToken;
    private Integer value;

    public TransactionDomain toDomain(RequestTransaction requestTransaction) {
        return TransactionDomain.builder()
                .value(requestTransaction.value)
                .creditCardToken(requestTransaction.creditCardToken)
                .userDocument(requestTransaction.userDocument)
                .build();
    }
}
