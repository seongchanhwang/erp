package io.galchung.erp.web;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.login.Login;
import io.galchung.erp.web.login.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {

    /**
     * 로그인 홈
     * Login Session 이 있는 경우 main 호출
     *
     * @param loginForm
     * @param loginMember
     * @param mv
     * @return
     */
    @GetMapping("/")
    public ModelAndView home(@ModelAttribute(name = "loginForm") LoginForm loginForm, @Login Member loginMember, ModelAndView mv){
        log.info("loginMember session = {}",loginMember);

        if(loginMember == null ){
            mv.setViewName("loginHome_back");
            return mv;
        }

        mv.setViewName("main");
        return mv;
    }

}
