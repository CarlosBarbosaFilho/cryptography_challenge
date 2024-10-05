package br.com.viaresults.cryptography.controller.request;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTransactionTest {


    @Nested
    class ToDomain {

        @Test
        void shouldMapCorrectly() {
            // arrange
            var request = RequestTransaction.builder()
                    .value(100)
                    .creditCardToken("0987654321")
                    .userDocument("03100721403")
                    .build();

            // act
            var domain = request.toDomain(request);

            // assert
            assertEquals(request.getValue(), domain.getValue());
            assertEquals(request.getUserDocument(), domain.getUserDocument());
            assertEquals(request.getCreditCardToken(), domain.getCreditCardToken());
        }
    }

}