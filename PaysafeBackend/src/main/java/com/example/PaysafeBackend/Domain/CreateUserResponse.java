package com.example.PaysafeBackend.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Existing_User")
public class CreateUserResponse {

    @JsonProperty("id")
    private String customerId;

    @JsonProperty("email")
    private String email;
}
