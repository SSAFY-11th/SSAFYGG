package com.ssafy.altf4.controller;

import com.ssafy.altf4.entity.champion.Champion;
import com.ssafy.altf4.entity.champion.FavoriteChampion;
import com.ssafy.altf4.entity.member.Member;
import com.ssafy.altf4.global.jwt.TokenManager;
import com.ssafy.altf4.service.ChampionService;
import com.ssafy.altf4.service.FavoriteService;
import com.ssafy.altf4.service.MemberService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/altf4/champion")
@RequiredArgsConstructor
public class ChampionController {

    private final MemberService memberService;

    private final ChampionService championService;

    private final FavoriteService favoriteService;

    private final TokenManager tokenManager;

    @PostMapping("/favorite/{champ_id}")
    public ResponseEntity<?> favoriteChampion(@PathVariable("champ_id") Long id, HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);

        Long memberId = (Long) tokenClaims.get("id");

        Member findMember = memberService.findById(memberId);

        Champion findChamp = championService.findById(id);

        FavoriteChampion favorite = favoriteService.favorite(findMember, findChamp);

        return ResponseEntity.ok(favorite);
    }
}
