package io.galchung.erp.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 로그인 컨트롤러
 * template 엔진 이용
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "loginHome";
    }


}

