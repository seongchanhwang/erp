package io.galchung.erp.web.exHandler.advice;

import io.galchung.erp.web.exHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "io.galchung.erp.web.login")
public class ApiControllerAdvice {

    private final MessageSource messageSource;

    public ApiControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 로그인 예외 핸들러
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<List<ErrorResult>> loginHandler(BindException e){
        e.getFieldErrors();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorResult> collect = fieldErrors.stream().map(error -> new ErrorResult(error.getCode(), messageSource.getMessage(error, Locale.KOREA), "")).collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.BAD_REQUEST);
    }

}
