package com.brq.projeto1.controller.exceptions;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorRequest {

    @JsonProperty("campo")
    private String field;

    @JsonProperty("mensagem")
    private String defaultMessage;






}
