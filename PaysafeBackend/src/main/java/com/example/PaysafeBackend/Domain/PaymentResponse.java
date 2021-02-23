package com.example.PaysafeBackend.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    @JsonProperty("status")
    private String status;
}
