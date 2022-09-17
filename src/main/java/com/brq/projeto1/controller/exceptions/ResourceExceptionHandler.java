package com.brq.projeto1.controller.exceptions;

import com.brq.projeto1.services.exceptions.DatabaseException;
import com.brq.projeto1.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
@RestController
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){

        String error = "Usuário não encontrado ou não existente";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){

        String error = "Database Error";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ExceptionApiCadastro.class)
    public final ResponseEntity<ExceptionResponseModel> handleApiExceptionApiCadastro(ExceptionApiCadastro ex, WebRequest request){
        ExceptionResponseModel error = new ExceptionResponseModel(ex.getCodigoErro(),ex.getMsgCustom(),ex.getMessage());

        error.setCampos(ex.getObjetosSaida());
        return new ResponseEntity<>(error,ex.getStatus());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
//        BindingResult bindingResult = ex.getBindingResult();
//        ExceptionResponseModel error = new ExceptionResponseModel(ex.get(),ex.getFieldError(),ex.getMessage());
//
//        error.setCampos(ex.getObjetosSaida());
//        return new ResponseEntity<>(error,ex.getStatus());
//    }

}
