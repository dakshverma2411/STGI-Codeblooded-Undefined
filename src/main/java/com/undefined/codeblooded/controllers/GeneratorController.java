package com.undefined.codeblooded.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.undefined.codeblooded.models.ApplicationUser;
import com.undefined.codeblooded.repositories.GeneratorRepository;
import com.undefined.codeblooded.requests.GeneratorRequest;
import com.undefined.codeblooded.responses.GeneratorResponse;
import com.undefined.codeblooded.services.GeneratorService;
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
import java.util.List;
import java.util.Map;

@RestController
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private UserService userService;

    @PostMapping("/generate")
    public ResponseEntity<GeneratorResponse> generate(@RequestBody GeneratorRequest request) throws JsonProcessingException {
        ApplicationUser user = loadUser();
        generatorService.persistData(user,request.getName(),request.getMapping(), request.getInput(), request.getOutput());
        GeneratorResponse res = GeneratorResponse.builder().status(200).code(request.getName()).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/project")
    public ResponseEntity<Map<String, Object>> getProjects(){
        ApplicationUser user = loadUser();
        List<Map<String, Object>> result = generatorService.getProjects(user);
        Map<String, Object> res = new HashMap<>();
        res.put("data", result);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private ApplicationUser loadUser() {
        return userService.getUserFromEmail("daksh");
    }

}