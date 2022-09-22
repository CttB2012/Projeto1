package com.brq.projeto1.controller.exceptions;

import com.brq.projeto1.services.exceptions.DatabaseException;
import com.brq.projeto1.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para lançar as exceções padronizadas
 * @author WGomes
 * @since 1.0
 */
@ControllerAdvice
@RestController
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método para Tratar o erro "Usuário não encontrado"
     * @param e
     * @param request
     * @return
     */
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorRequest> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorRequest errorRequest = ErrorRequest.builder()
                    .defaultMessage(error.getDefaultMessage())
                    .field(((FieldError) error).getField())
                    .build();
            errors.add(errorRequest);
        });


        ExceptionResponseModel problem = ExceptionResponseModel.builder()
                .dataHora(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(errors)
                .build();

        return new ResponseEntity<>(problem,HttpStatus.BAD_REQUEST);
    }
}
