package io.galchung.erp.domain.member;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

    @Primary
    @Repository
    public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m-> m.getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }

    @Override
    public void modify() {

    }

    @Override
    public void save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(),member);
    }

    @Override
    public void delete() {

    }
}
