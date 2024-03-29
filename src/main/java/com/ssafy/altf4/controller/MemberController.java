package com.ssafy.altf4.controller;

import com.ssafy.altf4.dto.MemberDto;
import com.ssafy.altf4.entity.Member;
import com.ssafy.altf4.entity.Role;
import com.ssafy.altf4.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/altf4/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/findAll")
    public ResponseEntity<List> findAll() {

        List<MemberDto.Response> dtoList = memberService.findAll().stream()
                .map(MemberDto.Response::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/find")
    public ResponseEntity<Member> findById(@RequestBody Long id){

        Member findMember = memberService.findById(id);

        return ResponseEntity.ok(findMember);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp (@RequestBody MemberDto.SignUp request){

        Member createMember = Member.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .nickName(request.getNickname())
                .role(Role.USER)
                .build();

        memberService.signup(createMember);

        return ResponseEntity.ok(true);
    }
}
