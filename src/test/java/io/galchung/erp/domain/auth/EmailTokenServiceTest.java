package io.galchung.erp.domain.auth;

import io.galchung.erp.domain.email.EmailService;
import io.galchung.erp.domain.email.EmailServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class EmailTokenServiceTest {


    @Autowired
    EmailTokenService emailTokenService;

    @Autowired
    EmailTokenRepository emailTokenRepository;
    @Test
    void createToken(){

        String memberId = "tjdcksghkd12@gmail.com";
        EmailToken emailToken = emailTokenService.create(memberId);
        // emailTokenService.save(emailToken);
        EmailToken getToken = emailTokenService.getToken(emailToken.getId());
        assertThat(emailToken.getId()).isEqualTo(getToken.getId());
    }

    @Test
    void changeToken(){
        String memberId = "tjdcksghkd12@gmail.com";
        EmailToken emailToken = emailTokenService.create(memberId);
        emailTokenRepository.save(emailToken);
        emailToken.expired = true;

        EmailToken changedToken = emailTokenRepository.findTokenById(emailToken.getId()).orElseGet(null);
        Assertions.assertThat(changedToken.expired).isTrue();

    }




}