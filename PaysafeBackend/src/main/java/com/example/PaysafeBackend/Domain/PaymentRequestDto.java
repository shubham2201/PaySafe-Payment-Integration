package com.example.PaysafeBackend.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private String merchantRefNum;

    private String customerId;

    private Integer amount;

    private String currencyCode;

    private String paymentHandleToken;
}
