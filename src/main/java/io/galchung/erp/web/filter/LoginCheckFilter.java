package io.galchung.erp.web.filter;

import io.galchung.erp.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.mail.Session;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 로그인 인증 필터
 */
@Slf4j
public class LoginCheckFilter implements Filter {

    private final String[] whitelist = {"/", "/login", "/logout", "/join", "/css/*", "/email/*" };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
        // 로그인 확인이 필요한 경로인지 체크
        if(isLoginCheckPath(requestURI)){
            HttpSession session = httpRequest.getSession();
            if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                log.info("미인증 사용자 요청 {}",requestURI);
                httpResponse.sendRedirect("/login?redirectURL="+requestURI);
                return;
            };
        }
        // 다음 필터 또는 서블렛으로 넘겨준다.
        chain.doFilter(request,response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}",requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
