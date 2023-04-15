package io.galchung.erp.config;

import io.galchung.erp.web.filter.LogFilter;
import io.galchung.erp.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


/**
 * 웹 관련 환경 설정
 * 필터 등록
 *
 */
@Configuration
public class WebConfig {

    @Bean
    FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
    @Bean
    FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(2);

        // 필터에서만 검사하도록 한다.
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        return filterRegistrationBean;

    }

}
