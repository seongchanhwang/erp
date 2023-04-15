package io.galchung.erp.web.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank(message = "ID")
    private String loginId;

    @NotBlank
    private String password;



}
