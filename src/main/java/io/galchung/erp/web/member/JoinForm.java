package io.galchung.erp.web.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class JoinForm {
    @NotBlank(message = "ID")
    @Email
    private String memberId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;



}
