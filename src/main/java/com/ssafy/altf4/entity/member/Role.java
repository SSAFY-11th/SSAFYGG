package com.ssafy.altf4.entity.member;

public enum Role {
    USER, ADMIN;

    public static Role from (String role){
        return Role.valueOf(role);
    }
}
