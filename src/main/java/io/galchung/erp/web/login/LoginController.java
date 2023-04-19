package io.galchung.erp.web.login;

import io.galchung.erp.domain.login.LoginService;
import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    //@GetMapping("/login")
    public ResponseEntity<LoginForm> loginForm(@ModelAttribute LoginForm loginForm){
        return new ResponseEntity<LoginForm>(loginForm, HttpStatus.OK );
    }


    /**
     * Member Login
     *
     * @param loginForm
     * @param bindingResult
     * @param request
     * @param
     * @return
     */
    // @ResponseBody
    @PostMapping("/api/login")
    public ResponseEntity<Member> login(@Valid @ModelAttribute LoginForm loginForm,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              @RequestParam(defaultValue = "/") String redirectURL
                              //ModelAndView mv
    ){
        // [유효성 검사]
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // [멤버 로그인]
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        log.info("loginMember={}",loginMember);

        // [회원 정보 검사]
        if(loginMember==null){
            bindingResult.reject("loginFail");
            //mv.setViewName("loginHome");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // [세션 등록]
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        // mv.setViewName("redirect:"+redirectURL);
        return new ResponseEntity<>(HttpStatus.OK);
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
