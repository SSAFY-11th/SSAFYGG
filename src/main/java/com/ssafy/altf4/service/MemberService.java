package com.ssafy.altf4.service;

import com.ssafy.altf4.dto.MemberDto;
import com.ssafy.altf4.entity.Member;
import com.ssafy.altf4.global.jwt.TokenDto;
import com.ssafy.altf4.global.jwt.TokenManager;
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

    private final TokenManager tokenManager;

    public Member findById(Long id){

        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NULL"));
    }

    public List<Member> findAll() {

        return memberRepository.findAll();
    }

    @Transactional
    public void signup(MemberDto.SignUp signUp){

        validEmail(signUp.getEmail());

        Member member = MemberDto.SignUp.from(signUp);

        memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id){

        memberRepository.deleteById(id);
    }

    private void validEmail(String email) {

        if(memberRepository.findByEmail(email).isPresent())
            throw new RuntimeException("meesage");
    }

    public void update(MemberDto.Update request) {


    }

    public TokenDto login(String email, String password){

        // 1. 로그인 정보를 통해서 멤버 객체 찾기
        Member findMember = memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 2. 멤버 정보를 통해서 tokenDto 생성
        TokenDto jwtTokenDto = tokenManager.createJwtTokenDto(findMember.getId(), findMember.getRole());

        // 3. 멤버 엔티티 안에 컬럼으로 지정된 토큰 정보 update
        findMember.updateToken(jwtTokenDto);

        return jwtTokenDto;
    }
}
