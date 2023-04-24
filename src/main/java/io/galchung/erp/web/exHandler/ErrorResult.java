package io.galchung.erp.web.exHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForReadableInstant;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;

    private Object data;
}
