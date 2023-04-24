package io.galchung.erp.config;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.login.Login;
import io.galchung.erp.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Login ArgumentResolver
 * HandlerMethodArgumentResolver 를 구현해 @Login 애너테이션이 붙은 파라미터에 로그인 정보를 매핑한다.
 *
 */
@Slf4j
public class LoginConfig implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);

        // 넘어온 파라미터의 타입이 Member 에 할당 가능한지 검사
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        /**
         * session 이면 로그인 하지 않은상태. null 리턴
         */
        if(session == null){
            return null;
        }


        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
