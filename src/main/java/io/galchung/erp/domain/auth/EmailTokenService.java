package io.galchung.erp.domain.auth;

import io.galchung.erp.domain.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailTokenService {
    private final EmailTokenRepository emailTokenRepository;

    private final EmailService emailService;

    /**
     * 토큰 생성
     * @param memberId
     * @return EmailToken
     */
    public EmailToken create(String memberId){
        EmailToken emailToken = EmailToken.emailToken(memberId);
        emailTokenRepository.save(emailToken);
        return emailToken;
    }

    /**
     * 토큰 반환
     *
     * @param tokenId
     * @return
     */
    public EmailToken getToken(String tokenId){
        Optional<EmailToken> foundToken = emailTokenRepository.findTokenById(tokenId);
        return foundToken.orElse(null);
    }


    /**
     * 토큰 유효성 검사
     * 현재 시간과 만료시간을 비교하여 만료여부를 검사한다.
     * @param tokenId
     * @return
     */
    public EmailToken validate(String tokenId) {

        EmailToken token = emailTokenRepository.findTokenById(tokenId).orElseThrow(NoSuchElementException::new);

        // 만료여부 검사
        if(LocalDateTime.now().isAfter(token.getExpirationTime())){
            token.expired = true;
        }

        return token;
    }
}
