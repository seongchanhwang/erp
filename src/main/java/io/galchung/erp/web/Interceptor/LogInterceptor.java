package io.galchung.erp.web.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String TRANSACTION_ID = "transactionId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("===============START================");

        // [요청 TransactionID]
        String logId = UUID.randomUUID().toString();
        request.setAttribute(TRANSACTION_ID,logId);

        // [호출할 컨트롤러 정보]
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            log.info("====> REQUEST [{}][{}][{}]",logId, requestURI, hm);
        } else if (handler instanceof ResourceHttpRequestHandler) {
            ResourceHttpRequestHandler hm = (ResourceHttpRequestHandler) handler;
            log.info("====> REQUEST [{}][{}][{}]",logId, requestURI, hm);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // [리턴 모델 조회]
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(TRANSACTION_ID);

        log.info("====> RESPONSE [{}][{}]", requestURI, logId  );
        if(ex != null){
            log.error("afterCompletion error !!", ex);
        }

        log.info("================END=================");

    }
}
