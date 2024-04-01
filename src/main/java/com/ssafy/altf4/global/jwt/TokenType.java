package com.ssafy.altf4.global.jwt;

import lombok.Getter;

@Getter
public enum TokenType {

    ACCESS("Access"), REFRESH("Refresh");

    TokenType(String type){
        this.type = type;
    }


    public static boolean isAccessToken(String tokenType){
        return TokenType.ACCESS.name().equals(tokenType);
    }

    private String type;
}
