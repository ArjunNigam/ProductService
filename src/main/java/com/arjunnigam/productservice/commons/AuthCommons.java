package com.arjunnigam.productservice.commons;


import com.arjunnigam.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private static RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public static boolean validateToken(String tokenValue) {
        // Call the UserService to validate the token
        // We will make a REST HTTP call from one service to another service. We will use RestTemplate

        UserDto userDto =  restTemplate.getForObject("http://localhost:8080/users/validate/" + tokenValue, UserDto.class);

        if(userDto==null){
            // Invalid token
            return false;
        }
        return true;

    }
}
