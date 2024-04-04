package com.ssafy.altf4.service;

import com.ssafy.altf4.entity.champion.Champion;
import com.ssafy.altf4.entity.champion.FavoriteChampion;
import com.ssafy.altf4.entity.member.Member;
import com.ssafy.altf4.repository.FavoriteChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteChampionRepository favoriteChampionRepository;

    public FavoriteChampion favorite(Member member, Champion champion) {

        FavoriteChampion favorite = FavoriteChampion.builder()
                .member(member)
                .champion(champion)
                .build();

        favoriteChampionRepository.save(favorite);

        return favorite;
    }
}
