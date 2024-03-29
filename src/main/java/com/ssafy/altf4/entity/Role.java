package com.ssafy.altf4.entity;

public enum Role {
    USER, ADMIN;

    public static Role from (String role){
        return Role.valueOf(role);
    }
}
