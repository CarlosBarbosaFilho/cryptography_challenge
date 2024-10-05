package br.com.viaresults.cryptography.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseTransaction {
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Integer value;
}
