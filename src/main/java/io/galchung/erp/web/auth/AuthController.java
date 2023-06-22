package io.galchung.erp.web.auth;

import io.galchung.erp.domain.auth.AuthService;
import io.galchung.erp.domain.auth.authKeyGenerator;
import io.galchung.erp.domain.email.EmailService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * 인증 컨트롤러
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final EmailService emailService;

    private final AuthService authService;

    /**
     * 메일 인증
     * @return
     */
    @PostMapping("/email/{memberId}")
    public ResponseEntity<Map<String,Object>> emailAuth(@PathVariable String memberId){
        String authKey = authService.sendAuthMail(memberId);
        Map<String,Object> result = new HashMap<>();
        result.put("authKey",authKey);
        return  ResponseEntity.ok().body(result);
    }




}
