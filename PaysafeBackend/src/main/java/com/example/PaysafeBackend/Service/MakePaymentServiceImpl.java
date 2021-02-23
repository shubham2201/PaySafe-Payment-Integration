package com.example.PaysafeBackend.Service;

import com.example.PaysafeBackend.Domain.PaymentDto;
import com.example.PaysafeBackend.Utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MakePaymentServiceImpl implements MakePaymentService{

    @Autowired
    private UserUtility userUtility;

    @Override
    public String makePayment(PaymentDto paymentDto) {
        return userUtility.makePayment(paymentDto);
    }
}
