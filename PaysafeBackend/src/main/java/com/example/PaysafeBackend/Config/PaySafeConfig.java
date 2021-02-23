package com.example.PaysafeBackend.Config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PaySafeConfig {

    @Value("${paySafe.createUserUrl}")
    private String createUserUrl;

    @Value("${paySafe.publicKey}")
    private String publicKey;

    @Value("${paySafe.privateKey}")
    private String privateKey;

    @Value("${paySafe.createSingleUserTokenUrlHead}")
    private String createSingleUserTokenUrlHead;

    @Value("${paySafe.createSingleUserTokenUrlTail}")
    private String createSingleUserTokenUrlTail;

    @Value("${paySafe.makePaymentUrl}")
    private String makePaymentUrl;
}
