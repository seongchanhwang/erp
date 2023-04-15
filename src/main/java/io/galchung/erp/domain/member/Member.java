package io.galchung.erp.domain.member;

import lombok.*;


@Getter
@Setter
@ToString
public class Member {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private Permission permission;

    public Member(String loginId, String password, String name, Permission permission) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.permission = permission;
    }

    public Member(MemberBuilder builder) {
        this.loginId = builder.loginId;
        this.password = builder.password;
    }

    public static class MemberBuilder{
        private String loginId;
        private String password;
        private String name;

        private Permission permission;

        public MemberBuilder(String loginId, String password) {
            this.loginId = loginId;
            this.password = password;
        }

        public MemberBuilder setName(String name){
            this.name=name;
            return this;
        }

        public MemberBuilder setPermission(Permission permission){
            this.permission = permission;
            return this;
        }

        public Member build(){
            return new Member(this);
        }
    }



}
