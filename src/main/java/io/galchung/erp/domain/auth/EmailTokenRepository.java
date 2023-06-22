package io.galchung.erp.domain.auth;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 이메일 인증 토큰 관련 기능
 */
public interface EmailTokenRepository {

    void save(EmailToken token);

    public Optional<EmailToken> findTokenById(String tokenId);

    void modifyStatus(String tokenId, LocalDateTime nowTime, boolean expiry);
}
