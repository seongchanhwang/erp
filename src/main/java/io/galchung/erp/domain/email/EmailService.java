package io.galchung.erp.domain.email;

import io.galchung.erp.domain.auth.EmailToken;
import org.springframework.mail.MailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {


    /**
     * 메일 전송
     * @param mailMessage
     */

    void sendMail(MailMessage mailMessage);

    /**
     * 인증 메일 전송
     */
    void sendAuthMail(EmailToken emailToken);


    void send(MimeMessage message);

    MimeMessage createMessage();
}
