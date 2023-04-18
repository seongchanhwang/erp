package io.galchung.erp.web.Interceptor;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null ){
            response.sendRedirect("/login?redirectURL="+requestURI);
            return false;
        }
        return true;

    }
}
