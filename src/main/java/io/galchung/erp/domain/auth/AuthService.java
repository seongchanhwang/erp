package io.galchung.erp.domain.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface AuthService {

    /**
     * 이메일 인증
     */
    String sendAuthMail(String email);


}
