package com.ssafy.altf4.dto;

import com.ssafy.altf4.entity.Member;
import com.ssafy.altf4.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


public class MemberDto {

    @Data
    @AllArgsConstructor
    @Builder
    public static class Request{

        private String email;

        private String password;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class Response{

        private Long id;

        private String username;

        private String email;

        private String password;

        private String nickname;

        @Enumerated(EnumType.STRING)
        private Role role;

        public static Response from(Member member){

            return Response.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .nickname(member.getNickName())
                    .role(member.getRole())
                    .build();
        }
    }

    @Data
    public static class Update{

        private String username;

        private String email;

        private String password;

        private String nickname;
    }

    @Data
    public static class SignUp{

        private String username;

        private String email;

        private String password;

        private String nickname;

        @Enumerated(EnumType.STRING)
        private Role role;

        public static Member from(MemberDto.SignUp dto){

            return Member.builder()
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .nickName(dto.getNickname())
                    .role(Role.USER)
                    .build();
        }
    }
}
