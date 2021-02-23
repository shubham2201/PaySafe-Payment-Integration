package com.example.PaysafeBackend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto {

    @JsonProperty("paymentHandleToken")
    private String paymentHandleToken;

    @JsonProperty("amount")
    private Integer amount;

    private String merchantRefNum;

    private String currencyCode;

    @JsonProperty("customerOperation")
    private String customerOperation;

    private String customerId;

    @JsonProperty("email")
    private String email;
}
