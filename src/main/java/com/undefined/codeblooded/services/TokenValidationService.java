package com.undefined.codeblooded.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {

    public boolean validate(String token){

        return true;
        /*
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=";
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(url + token, String.class);
        }
        catch (Exception e){
            return false;
        }

        return response.getStatusCode() == HttpStatus.OK;
        */
    }

}
