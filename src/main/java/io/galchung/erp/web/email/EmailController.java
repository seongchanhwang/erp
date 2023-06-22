package io.galchung.erp.web.email;

import io.galchung.erp.domain.auth.EmailToken;
import io.galchung.erp.domain.auth.EmailTokenService;
import io.galchung.erp.domain.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailTokenService emailTokenService ;
    private final EmailService emailService;

    /*@PostMapping("/{memberId}")
    public ResponseEntity sendMail(@PathVariable("memberId") String memberId ) {
        log.info("email={}",memberId);

        // [토큰 생성]
        EmailToken emailToken = emailTokenService.create(memberId);
        log.info("tokenId={}",emailToken.getId());

        // [인증 메일 전송]
        // emailService.sendAuthMail(emailToken);


        emailService.sendMail();
        return ResponseEntity.ok().body(true);
    }*/

    @GetMapping("/auth/{token}")
    public ResponseEntity authUser(@PathVariable("token") String tokenId, ModelAndView mv){
         mv.setViewName("redirect:/email/result");
        log.info("authToken={}",tokenId);

        // 토큰 검증
        try{
            EmailToken token = emailTokenService.validate(tokenId);
            return ResponseEntity.ok().body(token.expired);

        } catch (RuntimeException e){
            log.error("error={}",e);
            mv.addObject("errorMsg ","토큰 인증중 오류 발생" );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false) ;

        }


        // 토큰 만료시 메시지
    }

    @GetMapping("/result")
    public ModelAndView sendResult(ModelAndView mv){
        mv.setViewName("mail/result");
        return mv;
    }










}

