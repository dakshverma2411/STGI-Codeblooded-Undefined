package com.undefined.codeblooded.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private String access_token;

}
