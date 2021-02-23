package com.example.PaysafeBackend.Utility;

import com.example.PaysafeBackend.Config.PaySafeConfig;
import com.example.PaysafeBackend.Domain.*;
import com.example.PaysafeBackend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Random;

@Component
public class UserUtility {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaySafeConfig config;

    @Autowired
    private CreateUserRepository createUserRepository;

    public CreateUserResponse createUser(User user) {

        CreateCustomerRequestDto customer = new CreateCustomerRequestDto();
        customer.setEmail(user.getEmail());
        customer.setMerchantCustomerId(user.getFirstName() + Math.random());
        String createUserUrl = config.getCreateUserUrl();
        HttpEntity<CreateCustomerRequestDto> request = new HttpEntity<CreateCustomerRequestDto>(customer, getHeader());
        ResponseEntity<CreateUserResponse> createdUser = restTemplate.exchange(createUserUrl, HttpMethod.POST, request, CreateUserResponse.class);
        createUserRepository.save(createdUser.getBody());
        return createdUser.getBody();
    }

    public String createSingleUserToken(String customerId){

        CreateSingleUserTokenDto tokenDto = new CreateSingleUserTokenDto();
        ArrayList<String> paymentType = new ArrayList<>();
        Random random = new Random();
        paymentType.add("CARD");
        tokenDto.setPaymentTypes(paymentType);
        tokenDto.setMerchantRefNum("REF" + random.nextInt(100000));
        String createSingleUserTokenUrl = config.getCreateSingleUserTokenUrlHead() + customerId + config.getCreateSingleUserTokenUrlTail();
        HttpEntity<CreateSingleUserTokenDto> request = new HttpEntity<CreateSingleUserTokenDto>(tokenDto, getHeader());
        ResponseEntity<TokenResponse> generatedToken = restTemplate.exchange(createSingleUserTokenUrl, HttpMethod.POST, request, TokenResponse.class);
        return generatedToken.getBody().getSingleUseCustomerToken();
    }

    public String makePayment(PaymentDto paymentDto) {
        Random random = new Random();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setCurrencyCode("USD");
        paymentRequestDto.setMerchantRefNum("Merchant" + random.nextInt(100000));
        paymentRequestDto.setAmount(paymentDto.getAmount());
        paymentRequestDto.setPaymentHandleToken(paymentDto.getPaymentHandleToken());
        if(paymentDto.getCustomerOperation() != null) {
            paymentRequestDto.setCustomerId(createUserRepository.findByEmail(paymentDto.getEmail()).getCustomerId());
        }
        String makePaymentUrl = config.getMakePaymentUrl();
        HttpEntity<PaymentRequestDto> request = new HttpEntity<PaymentRequestDto>(paymentRequestDto, getHeader());
        try {
            ResponseEntity<PaymentResponse> paymentResponse = restTemplate.exchange(makePaymentUrl, HttpMethod.POST, request, PaymentResponse.class);
            if (paymentResponse.getBody().getStatus().equals("COMPLETED")) return paymentResponse.getBody().getStatus();
        } catch (HttpClientErrorException ex) {
            return ex.getMessage();
        }
        return null;
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Basic " + config.getPrivateKey());
        headers.add("Simulator", "EXTERNAL");
        return headers;
    }
}
