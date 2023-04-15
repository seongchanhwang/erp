package io.galchung.erp.domain.auth;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryEmailTokenRepository implements EmailTokenRepository {

    private static Map<String, EmailToken> tokenStore = new ConcurrentHashMap<>();

    @Override
    public void save(EmailToken token) {
        tokenStore.put(token.getId(), token);
    }

    @Override
    public Optional<EmailToken> findTokenById(String tokenId) {
        return Optional.ofNullable(tokenStore.get(tokenId));
    }

    @Override
    public void modifyStatus(String tokenId, LocalDateTime nowTime, boolean expiry) {
        EmailToken emailToken = tokenStore.get(tokenId);
        if(nowTime.isAfter(emailToken.getExpirationTime())){
            emailToken.expired = expiry;
        }


    }

}
