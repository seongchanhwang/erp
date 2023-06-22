package io.galchung.erp.web.exHandler.advice;

import io.galchung.erp.web.exHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "io.galchung.erp.web.login")
public class ApiLoginControllerAdvice {

    private final MessageSource messageSource;

    public ApiLoginControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * API 로그인
     * ErrorResult 에 오류코드, 메시지를 넣어 A
     *
     * @param e
     * @return ErrorResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public List<ErrorResult> loginHandler(BindException e){

        log.error("[loginFail] ex ",e );

        List<ErrorResult> collect = null;

        if (e.hasFieldErrors()){
            // Field Error 처리
            List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
            collect = fieldErrors.stream()
                    .map(error -> new ErrorResult(error.getCode(), messageSource.getMessage(error, Locale.KOREA), ""))
                    .collect(Collectors.toList());

        } else if (e.hasGlobalErrors()){
            // Object Error 처리
            List<ObjectError> globalErrors = e.getBindingResult().getGlobalErrors();
            collect = globalErrors.stream().map(error -> new ErrorResult(error.getCode(), messageSource.getMessage(error, Locale.KOREA), ""))
                    .collect(Collectors.toList());
        }

        return collect;
    }

}
