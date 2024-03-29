package com.ssafy.altf4.service;

import com.ssafy.altf4.entity.Member;
import com.ssafy.altf4.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(Long id){

        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NULL"));
    }

    public List<Member> findAll() {

        return memberRepository.findAll();
    }

    @Transactional
    public void signup(Member member){

        validEmail(member.getEmail());

        memberRepository.save(member);
    }

    private void validEmail(String email) {

        if(memberRepository.findByEmail(email).isPresent())
            throw new RuntimeException("meesage");
    }
}
