package io.galchung.erp.domain.email;

import io.galchung.erp.domain.auth.EmailToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

    @Value("${AdminMail.id}")
    private String fromEmail;


    @Override
    public void sendMail(MailMessage mailMessage) {

    }

    @Async
    @Override
    public void sendAuthMail(EmailToken emailToken) {
        MimeMessage message = this.createMessage(emailToken);
        javaMailSender.send(message);

    }

    private MimeMessage createMessage(EmailToken emailToken){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(message,true,"UTF-8");

            mailHelper.setTo(emailToken.getMemberId());;
            mailHelper.setSubject("이메일 인증 테스트");

            Context context = new Context();
            HashMap<String,Object> emailValues = new HashMap<>();
            emailValues.put("name","갈현교회 청년부");
            emailValues.put("token",emailToken.getId());
            context.setVariables(emailValues);

            String html = templateEngine.process("mail/index", context);
            mailHelper.setText(html ,true);

            //이미지 삽입
            mailHelper.addInline("main-img", new ClassPathResource("static/images/mail/mail-main.jpeg"));
            mailHelper.addInline("erp-logo", new ClassPathResource("static/images/logo/erp_linkedin_logo.png"));
            mailHelper.addInline("youtube", new ClassPathResource("static/images/sns/youtube.png"));
            mailHelper.addInline("instagram", new ClassPathResource("static/images/sns/instagram.png"));
            mailHelper.addInline("galchung", new ClassPathResource("static/images/sns/galchung.png"));
            mailHelper.setFrom(fromEmail);
            return message;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    };



}


