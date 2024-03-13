package com.ssafy.altf4.repository;

import com.ssafy.altf4.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Long, Member> {

}
