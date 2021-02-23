package com.example.PaysafeBackend.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequestDto {

    private String email;

    private String merchantCustomerId;

//    private String firstName;
//
//    private String lastName;
//
//    private DateOfBirth dateOfBirth;
}
