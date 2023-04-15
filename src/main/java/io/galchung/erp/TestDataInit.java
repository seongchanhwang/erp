package io.galchung.erp;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.domain.member.MemberRepository;
import io.galchung.erp.domain.member.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        Member admin = new Member("admin","admin!","관리자", Permission.ADMIN);
        Member manager = new Member("manager","manager!","임원", Permission.MANAGER);
        Member leader = new Member("leader","leader!","리더", Permission.LEADER);
        Member basic = new Member("basic","basic!","일반", Permission.BASIC);



        memberRepository.save(admin);
        memberRepository.save(manager);
        memberRepository.save(basic);
        memberRepository.save(leader);
    }
}
