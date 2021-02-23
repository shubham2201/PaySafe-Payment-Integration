package com.example.PaysafeBackend.Service;

import com.example.PaysafeBackend.Domain.PaymentDto;

public interface MakePaymentService {

    public String makePayment(PaymentDto paymentDto);
}
