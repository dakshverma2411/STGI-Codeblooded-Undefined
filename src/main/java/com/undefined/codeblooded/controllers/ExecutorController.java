package com.undefined.codeblooded.controllers;

import com.undefined.codeblooded.models.ApplicationUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.undefined.codeblooded.requests.ExecutorRequest;
import com.undefined.codeblooded.responses.ExecutorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
public class ExecutorController {
    @PostMapping("/execute")
    public ResponseEntity<ExecutorResponse> execute(@RequestBody ExecutorRequest request){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.createObjectNode();
        ((ObjectNode) node).put("id", request.getProjectId());
        ((ObjectNode) node).put("value", request.getInput());
        ExecutorResponse res = ExecutorResponse.builder().status(200).output(node).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}