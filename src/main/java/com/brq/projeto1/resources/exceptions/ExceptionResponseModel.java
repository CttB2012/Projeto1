package com.brq.projeto1.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
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

    public ExceptionResponseModel() {
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
