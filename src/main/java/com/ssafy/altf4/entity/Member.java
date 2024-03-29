package com.ssafy.altf4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
}
