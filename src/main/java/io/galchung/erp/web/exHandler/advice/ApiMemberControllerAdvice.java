package io.galchung.erp.web.exHandler.advice;

import io.galchung.erp.web.exHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "io.galchung.erp.web.member")
public class ApiMemberControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResult> joinHandler(BindException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        log.info("fieldErrors={}",fieldErrors);
        // List<Object> collect = fieldErrors.stream().map(error -> )).collect(Collectors.toList());



        return null;

    }
}
