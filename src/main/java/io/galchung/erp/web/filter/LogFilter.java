package io.galchung.erp.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();

        // 요청 트랜잭션 번호 생성
        String uuid = UUID.randomUUID().toString();
        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request,response);
        } catch (Exception e) {
            // 여기서 예외 처리하면 정상흐름 처럼 동작하기 때문에 WAS 까지 던져준다.
            throw e ;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);

        }



    }
}
