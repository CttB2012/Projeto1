package com.brq.projeto1.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

/**
 * Classe para criar o padrão para lançar as Exceções
 * @author WGomes
 * @since release 1.0
 */
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1l;

    /**
     * Parâmetros que devem ser utilizados para criar as Exceções
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public  StandardError() {
     }

    /**
     * Sobrecarga dos Parâmetros que devem ser utilizados para criar as Exceções personalizadas
     * @param timeStamp
     * @param status
     * @param error
     * @param message
     * @param path
     */
    public StandardError(Instant timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Getters e Setters dos parâmetros
     * @return
     */
    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
