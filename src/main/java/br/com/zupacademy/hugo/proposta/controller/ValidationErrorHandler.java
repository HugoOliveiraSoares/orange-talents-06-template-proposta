package br.com.zupacademy.hugo.proposta.controller;

import br.com.zupacademy.hugo.proposta.controller.dto.ValidationErrorsOutputDto;
import br.com.zupacademy.hugo.proposta.validator.ApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder){

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception){

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrorsOutputDto handleValidationError(BindException exception){

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);

    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ValidationErrorsOutputDto> handleApiErrorException(ApiErrorException exception){

        ValidationErrorsOutputDto outputDto = new ValidationErrorsOutputDto();
        outputDto.addError(exception.getReason());

        return ResponseEntity.status(exception.getHttpStatus()).body(outputDto);

    }

    private ValidationErrorsOutputDto buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {

        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();

        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error ->{
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });

        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
