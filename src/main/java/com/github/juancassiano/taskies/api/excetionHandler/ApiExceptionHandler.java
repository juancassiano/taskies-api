package com.github.juancassiano.taskies.api.excetionHandler;

import java.io.StringWriter;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.juancassiano.taskies.domain.exception.CategoryNotFoundException;
import com.github.juancassiano.taskies.domain.exception.EntityInUseException;
import com.github.juancassiano.taskies.domain.exception.TaskNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;


@RestControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    
    URI path = URI.create("http://localhost:8080/errors");
  
    @ExceptionHandler(TaskNotFoundException.class)
    private ResponseEntity<ProblemDetail> handleTaskNotFoundException(TaskNotFoundException exception){
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,sw.toString()
        );
        problemDetail.setInstance(URI.create("/task-not-found"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setProperty("descricao", "Essa Task não foi encontrada ou não existe");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(EntityInUseException.class)
    private ResponseEntity<ProblemDetail> handleEntityInUseException(EntityInUseException exception){
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,sw.toString()
        );
        problemDetail.setInstance(URI.create("/entity-in-use"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setProperty("descricao", "Essa Entidade está em uso");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<ProblemDetail> handleCategoryNotFoundException(CategoryNotFoundException exception){
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,sw.toString()
        );
        problemDetail.setInstance(URI.create("/category-not-found"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setProperty("descricao", "Essa Categoria não foi encontrada ou não existe");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errorMessages.add(errorMessage);
        }
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                sw.toString());
        problemDetail.setTitle("Erro de validação");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errorMessages);
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(HttpMessageNotReadableException exception){
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                sw.toString());
        problemDetail.setTitle("Erro de validação");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);

    }
}
