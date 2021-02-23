package com.example.PaysafeBackend.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateSingleUserTokenDto {

   private String merchantRefNum;

   private List<String> paymentTypes;
}
