package io.galchung.erp.web.login;

import io.galchung.erp.domain.login.LoginService;
import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "/loginHome";
    }


    /**
     * Member Login
     *
     * @param loginForm
     * @param bindingResult
     * @param request
     * @param mv
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute LoginForm loginForm,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              @RequestParam(defaultValue = "/") String redirectURL,
                              ModelAndView mv
    ){
        // [유효성 검사]
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            mv.setViewName("loginHome");
            return mv;
        }

        // [멤버 로그인]
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        log.info("loginMember={}",loginMember);

        // [회원 정보 검사]
        if(loginMember==null){
            bindingResult.reject("loginFail");
            mv.setViewName("loginHome");
            return mv;
        }

        // [세션 등록]
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        mv.addObject("member",loginMember);
        mv.setViewName("redirect:"+redirectURL);
        return mv;
    }

    /**
     * Member Logout
     * @param request
     * @param mv
     * @return
     */
    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, ModelAndView mv ){

        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        mv.setViewName("redirect:/login");
        return mv;
    }




}
