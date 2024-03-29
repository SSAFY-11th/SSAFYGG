package com.ssafy.altf4.repository;

import com.ssafy.altf4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<Member> findByUsernameContaining(String username);

    Optional<Member> findByEmail(String email);
}
