package com.mercadolibre.integrativeproject.handlers;

import com.mercadolibre.integrativeproject.dtos.ErrorMessageDTO;
import com.mercadolibre.integrativeproject.dtos.InvalidParamsDTO;
import com.mercadolibre.integrativeproject.exceptions.MissingParamsException;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.exceptions.TemperatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler for exception handling and return of parameterized messages.
 *
 * @author Samuel Stalschus
 */
@RestControllerAdvice
public class AppExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public InvalidParamsDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.put(fieldName, errorMessage);
        });
        return InvalidParamsDTO.builder()
                .message("Invalid params")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .arguments(errors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation -> {
            errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        });
        return errors;
    }

    @ExceptionHandler(value = MissingParamsException.class)
    protected ResponseEntity<Object> handlePersistencia(MissingParamsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handlePersistencia(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = RepositoryException.class)
    protected ResponseEntity<Object> handlePersistencia(RepositoryException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = TemperatureException.class)
    protected ResponseEntity<Object> handlePersistencia(TemperatureException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
