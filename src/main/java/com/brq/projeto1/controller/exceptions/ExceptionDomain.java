package com.brq.projeto1.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDomain {

    @JsonProperty("status")
    private HttpStatus httpStatus;
    @JsonProperty("dataHora_erro")
    private LocalDateTime dataHora;
    @JsonProperty("mensagem")
    private String mensagem;
    @JsonProperty("erros")
    private List<ErrorRequest> error;

}
