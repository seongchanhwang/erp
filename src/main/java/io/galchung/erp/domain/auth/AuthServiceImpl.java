package io.galchung.erp.domain.auth;

import io.galchung.erp.config.EmailConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;


    @Override
    public String sendAuthMail(String memberMail) {
        String authKey = authKeyGenerator.createKey();
        MimeMessage message = this.createMessage(memberMail, authKey);
        javaMailSender.send(message);
        return authKey;
    }

    /**
     * 인증 메시지를 생성한다.
     * @param memberMail
     * @param authKey
     * @return
     */
    private MimeMessage createMessage(String memberMail,String authKey) {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();

        try {
            MimeMessageHelper mailHelper = new MimeMessageHelper(message,true,"UTF-8");


            HashMap<String,Object> emailValues = new HashMap<>();
            emailValues.put("name","MARU-ERP");
            emailValues.put("authKey", authKey);
            context.setVariables(emailValues);

            // 메일 템플릿에 변수를 바인딩
            String html = templateEngine.process("mail/index", context);


            mailHelper.setTo(memberMail);
            mailHelper.setSubject("이메일 인증 테스트");
            mailHelper.setText(html ,true);

            //이미지 삽입
            /*mailHelper.addInline("main-img", new ClassPathResource("static/images/mail/mail-main.jpeg"));
            mailHelper.addInline("erp-logo", new ClassPathResource("static/images/logo/erp_linkedin_logo.png"));
            mailHelper.addInline("youtube", new ClassPathResource("static/images/sns/youtube.png"));
            mailHelper.addInline("instagram", new ClassPathResource("static/images/sns/instagram.png"));
            mailHelper.addInline("galchung", new ClassPathResource("static/images/sns/galchung.png"));*/



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return message;


    }

}
