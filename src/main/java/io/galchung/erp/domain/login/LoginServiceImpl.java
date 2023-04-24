package io.galchung.erp.domain.login;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.domain.member.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Basic Login Service
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository repository;

    @Override
    public Member login(String loginId, String password) {
        return repository.findByLoginId(loginId)
                .filter(m-> m.getPassword().equals(password))
                .stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
