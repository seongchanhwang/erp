package io.galchung.erp.domain.login;

import io.galchung.erp.domain.member.Member;
import io.galchung.erp.web.login.LoginForm;

public interface LoginService {

    /**
     * Member login
     *
     * @param loginId
     * @param password
     * @return
     */
    public Member login(String loginId, String password);



}
