package io.galchung.erp.domain.auth;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailTokenRepository {

    void save(EmailToken token);

    public Optional<EmailToken> findTokenById(String tokenId);

    void modifyStatus(String tokenId, LocalDateTime nowTime, boolean expiry);
}
