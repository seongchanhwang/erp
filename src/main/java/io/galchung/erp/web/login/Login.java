package io.galchung.erp.web.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 애너테이션
 * 애너테이션이 붙은 객체에 멤버의 로그인 ID를 매핑한다.
 */
@Target(ElementType.PARAMETER)      // 파라미터에 적용
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 애노테이션 정보 유지
public @interface Login {
}
