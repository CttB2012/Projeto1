package com.brq.projeto1.controller.exceptions;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Classe para criar uma 'Request' de erros
 * @author WGomes
 * @since Release 1.0
 */
@Getter
@Setter
@Builder
public class ErrorRequest {

    @JsonProperty("campo")
    private String field;

    @JsonProperty("mensagem")
    private String defaultMessage;






}
