package io.galchung.erp.domain.auth;


import com.sun.mail.imap.Rights;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 보안키 생성자
 * 비밀번호 찾기, 회원 인증 기능에 사용될 RANDOM 난수 생성
 */
@Component
public class authKeyGenerator {



    public static String createKey(){

        // 아스키코드 97 : 소문자 알파뱃 a
        int leftLimit = 97;

        // 아스키코드 122 : 소문자 알파뱃 z
        int rightLimit = 122;

        // 생성할 글자 수
        int targetStringLength = 10;
        Random random = new Random();

        // key 생성
        return random.ints(leftLimit, rightLimit)
                .limit(targetStringLength)
                .collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }


}
