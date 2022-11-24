package com.undefined.codeblooded.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneratorRequest {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private JsonNode input;

    @JsonProperty(required = true)
    private JsonNode output;

    @JsonProperty(required = true)
    private String mapping;

}