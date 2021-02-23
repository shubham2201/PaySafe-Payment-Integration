package com.example.PaysafeBackend.Controller;

import com.example.PaysafeBackend.Domain.PaymentDto;
import com.example.PaysafeBackend.Service.MakePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PaymentController {

    @Autowired
    private MakePaymentService makePaymentService;

    @PostMapping(value = "/makePayment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String makePayment(@RequestBody PaymentDto paymentDto ) {

        return makePaymentService.makePayment(paymentDto);
    }
}
