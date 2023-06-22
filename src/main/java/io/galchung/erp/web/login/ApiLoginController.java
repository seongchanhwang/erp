package io.galchung.erp.web.login;

import io.galchung.erp.domain.login.LoginService;
import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * api 요청 로그인 컨트롤러
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiLoginController {

    private final LoginService loginService;




    /**
     * Member Login By ModelAttribute
     * BindingResult 와 @ModelAttribute 를 이용한 로그인 컨트롤러
     * bindingResult 로 오류를 바인딩하여 view 로 넘긴다.
     *
     * @deprecated
     * @param loginForm
     * @param bindingResult
     * @param request
     * @param
     * @return
     */
    // @ResponseBody
    // @PostMapping("/api/login")
    public ResponseEntity<Member> loginV1(@Valid @ModelAttribute LoginForm loginForm,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              @RequestParam(defaultValue = "/") String redirectURL

    ){

        log.info("loginForm={}" ,loginForm);

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
     * API 로그인
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm, BindingResult bindingResult,
                                        HttpServletRequest request
                                        //,@RequestParam(defaultValue = "/") String redirectURL
    ) throws BindException {

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        log.info("loginForm = {}", loginForm);

        // [멤버 로그인]
        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
        log.info("loginMember = {}",loginMember);

        // [회원 정보 검사]
        if(loginMember==null){
            log.info("target={}",bindingResult.getTarget());
            bindingResult.reject("loginFail","로그인 실패, 다시 시도해주세요");
            throw new BindException(bindingResult);
        }

        // [세션 등록]
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        return ResponseEntity.ok().body("객체 검증 성공");
    }

    /**
     * Member Logout
     *
     * /login 페이지로 리다이렉트
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

        mv.setViewName("redirect:/api/login");
        return mv;
    }




}
