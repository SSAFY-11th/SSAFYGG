package com.ssafy.altf4.entity;

import com.ssafy.altf4.dto.MemberDto;
import com.ssafy.altf4.global.util.DateTimeUtils;
import com.ssafy.altf4.global.jwt.TokenDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id // 기본 키 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자 값 자동 생성
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String email;

    private String password;

    private String nickName;

    @Enumerated(EnumType.STRING) // enum 사용, EnumType.STRING을 써야 됨, 안 쓰면 순서 가져옴
    private Role role;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    public void updateMember(MemberDto.Update dto){

        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickName = dto.getNickname();
    }

    public void updateToken(TokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}
