package io.galchung.erp.web.member;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.domain.member.MemberRepository;
import io.galchung.erp.domain.member.Permission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository repository;
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("joinForm") JoinForm joinForm){
        return "members/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinForm joinForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("error={}",bindingResult);
            return "/members/joinForm";
        }

        // 멤버 생성. 생성시 권한은 BASIC.
        Member member = new Member.MemberBuilder(joinForm.getMemberId(), joinForm.getPassword())
                .setName(joinForm.getName())
                .setPermission(Permission.BASIC)
                .build();

        // 멤버 저장
        repository.save(member);

        return "redirect:/";
    }


}
