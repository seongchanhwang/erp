package io.galchung.erp.config;

import io.galchung.erp.web.Interceptor.LogInterceptor;
import io.galchung.erp.web.Interceptor.LoginCheckInterceptor;
import io.galchung.erp.web.filter.LogFilter;
import io.galchung.erp.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;


/**
 * 웹 관련 환경 설정
 * 필터, 인터셉터 설정
 *
 * @interceptor 로그인 체크, 로그 인터셉터
 *
 *
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())
                        .order(1)
                        .addPathPatterns("/**")
                        .excludePathPatterns( "/css/**","/images/**","/error", "/js/**","/*.ico");

        registry.addInterceptor(new LoginCheckInterceptor())
                        .order(2)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/error","/login","/logout","/join/**","/css/**","/images/**", "/js/**","/*.ico");
    }

    //@Bean
    FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
    //@Bean
    FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(2);

        // 필터에서만 검사하도록 한다.
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        return filterRegistrationBean;

    }

}
