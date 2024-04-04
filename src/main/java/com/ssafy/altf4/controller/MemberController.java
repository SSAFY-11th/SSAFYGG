package com.ssafy.altf4.controller;

import com.ssafy.altf4.dto.MemberDto;
import com.ssafy.altf4.entity.member.Member;
import com.ssafy.altf4.global.jwt.TokenDto;
import com.ssafy.altf4.global.jwt.TokenManager;
import com.ssafy.altf4.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
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

    private final TokenManager tokenManager;

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

        memberService.signup(request);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> update (@RequestBody MemberDto.Update request){

        memberService.update(request);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberDto.Request request){

        TokenDto token = memberService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/access-token/issue")
    public ResponseEntity<TokenDto.AccessTokenDto> createAccessTokenByRefreshToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        String refreshToken = authorization.split(" ")[1];

        TokenDto.AccessTokenDto accessTokenResponseDTO = memberService.createAccessTokenByRefreshToken(refreshToken);

        return ResponseEntity.ok(accessTokenResponseDTO);
    }
}
