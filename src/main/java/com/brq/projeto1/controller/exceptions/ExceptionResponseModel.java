package com.brq.projeto1.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe com todos os parâmetros para a construção de mensagens personalizadas de exceção
 * @author WGomes
 * @since Release 1.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("mensagemDetalhada")
    private String mensagemDetalhada;

    @JsonProperty("campos")
    private List<?> campos = new ArrayList<>();

    @JsonProperty("status")
    private HttpStatus httpStatus;
    @JsonProperty("dataHora_erro")
    private LocalDateTime dataHora;
    @JsonProperty("erros")
    private List<ErrorRequest> error;

    /**
     * Construtores e suas respectivas sobrecargas
     * @param httpStatus
     * @param dataHora
     * @param error
     */

    public ExceptionResponseModel(HttpStatus httpStatus, LocalDateTime dataHora, List<ErrorRequest> error) {
        this.httpStatus = httpStatus;
        this.dataHora = dataHora;
        this.error = error;
    }

    public ExceptionResponseModel(String codigo, String mensagem, String mensagemDetalhada) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.mensagemDetalhada = mensagemDetalhada;
    }

    public ExceptionResponseModel(String codigo, String mensagem, String mensagemDetalhada, List<?> campos) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.mensagemDetalhada = mensagemDetalhada;
        this.campos = campos;
    }

    /**
     * Getters e Setters
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagemDetalhada() {
        return mensagemDetalhada;
    }

    public void setMensagemDetalhada(String mensagemDetalhada) {
        this.mensagemDetalhada = mensagemDetalhada;
    }

    public List<?> getCampos() {
        return campos;
    }

    public void setCampos(List<?> campos) {
        this.campos = campos;
    }
}
