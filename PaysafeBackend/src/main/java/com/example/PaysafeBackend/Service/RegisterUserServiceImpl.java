package com.example.PaysafeBackend.Service;

import com.example.PaysafeBackend.Config.PaySafeConfig;
import com.example.PaysafeBackend.Domain.CreateCustomerRequestDto;
import com.example.PaysafeBackend.Domain.CreateUserRepository;
import com.example.PaysafeBackend.Domain.CreateUserResponse;
import com.example.PaysafeBackend.Entity.User;
import com.example.PaysafeBackend.Utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private CreateUserRepository createUserRepository;

    @Autowired
    private UserUtility userUtility;

    @Override
    public String registerUser(User user) {

        String email = user.getEmail();
        CreateUserResponse savedUser = createUserRepository.findByEmail(email);
        if(savedUser == null) {
            savedUser = userUtility.createUser(user);
        }
        String singleUseCustomerToken = userUtility.createSingleUserToken(savedUser.getCustomerId());
        return singleUseCustomerToken;
    }
}
