package io.galchung.erp.domain.auth;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 이메일 인증 토큰
 *
 * 만료 시간 5분
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailToken {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME = 5L;    // 이메일 토큰 만료 시간
    private String id;                     // 토큰 식별자
    private String memberId;               // 회원 ID(이메일)
    public boolean expired;               // 만료여부
    private LocalDateTime expirationTime;  // 만료시간

    public static EmailToken emailToken(String memberId){
        EmailToken emailToken = new EmailToken();
        emailToken.expirationTime = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME);
        emailToken.id = UUID.randomUUID().toString();
        emailToken.expired = false;
        emailToken.memberId = memberId;
    return emailToken;
    }

    public void isExpired(){
        expired = true;
    }


}
