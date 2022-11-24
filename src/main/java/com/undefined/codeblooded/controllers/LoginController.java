package com.undefined.codeblooded.controllers;

import com.undefined.codeblooded.exceptions.InvalidAccessTokenException;
import com.undefined.codeblooded.models.ApplicationUser;
import com.undefined.codeblooded.requests.LoginRequest;
import com.undefined.codeblooded.responses.LoginResponse;
import com.undefined.codeblooded.services.TokenValidationService;
import com.undefined.codeblooded.services.UserService;
import com.undefined.codeblooded.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenValidationService tokenValidationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws InvalidAccessTokenException {

        String email = request.getEmail();
        String access_token = request.getAccess_token();

        if(!tokenValidationService.validate(access_token)){
            throw new InvalidAccessTokenException();
        }

        ApplicationUser user = userService.getUserFromEmail(email);
        if(user==null){
            user = ApplicationUser.builder()
                    .email(email)
                    .build();
        }

        user.setAccess_token(access_token);

        userService.saveToDb(user);

        String jwt_token = jwtUtil.generateToken(user);

        LoginResponse res = LoginResponse.builder()
                .status(200)
                .msg("Logged In Successfully")
                .jwt_token(jwt_token)
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);



    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test(){
        Map res = new HashMap();
        res.put("status", "running");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
