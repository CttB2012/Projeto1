package com.brq.projeto1.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ExceptionApiCadastro extends ResponseStatusException {

    private static final long serialVersionUID = 1l;

    private String codigoErro;
    private Object objetoEntrada;
    private String msgCustom;
    private List<?> objetosSaida;

    public ExceptionApiCadastro(HttpStatus status, String codigoErro) {
        super(status, (String)null, (Throwable)null );
        this.codigoErro = codigoErro;
        this.objetoEntrada = null;
        this.msgCustom = ExceptionMessage.buscarMessage(codigoErro);
    }

    public ExceptionApiCadastro(HttpStatus status, Object objetoEntrada ,String codigoErro, String mensagemFormatada) {
        super(status, (String)null, (Throwable)null );
        this.codigoErro = codigoErro;
        this.objetoEntrada = objetoEntrada;
        this.msgCustom = mensagemFormatada;
    }

    public ExceptionApiCadastro(HttpStatus status, String codigoErro, Object... args) {
        super(status, (String)null, (Throwable)null );
        Assert.notNull(args, "Args is required");
        this.codigoErro = codigoErro;
        this.objetoEntrada = null;
        this.msgCustom = String.format(ExceptionMessage.buscarMessage(codigoErro), args);
    }

    public ExceptionApiCadastro(HttpStatus status, String codigoErro, String arg1) {
        super(status, (String)null, (Throwable)null );
        this.codigoErro = codigoErro;
        this.objetoEntrada = null;
        this.msgCustom = String.format(ExceptionMessage.buscarMessage(codigoErro), arg1);
    }

    public ExceptionApiCadastro(HttpStatus status, String reason,String codigoErro,Object objetoEntrada ,@Nullable Throwable cause) {
        super(status, reason, cause );
        this.codigoErro = codigoErro;
        this.objetoEntrada = objetoEntrada;
        this.msgCustom = ExceptionMessage.buscarMessage(codigoErro);
    }
    public ExceptionApiCadastro(HttpStatus status,String codigoErro, List<?>objetosSaida) {
        super(status, (String)null, (Throwable)null );
        this.codigoErro = codigoErro;
        this.objetosSaida = objetosSaida;
        this.msgCustom = ExceptionMessage.buscarMessage(codigoErro);
    }
    public Object getObjetoEntrada(){
        return this.objetoEntrada;
    }

    public void setObjetoEntrada(Object objetoEntrada){
        this.objetoEntrada = objetoEntrada;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getMsgCustom() {
        return msgCustom;
    }

    public void setMsgCustom(String msgCustom) {
        this.msgCustom = msgCustom;
    }

    public List<?> getObjetosSaida() {
        return objetosSaida;
    }

    public void setObjetosSaida(List<?> objetosSaida) {
        this.objetosSaida = objetosSaida;
    }
}
