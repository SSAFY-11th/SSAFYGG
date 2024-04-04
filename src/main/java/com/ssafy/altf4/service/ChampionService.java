package com.ssafy.altf4.service;

import com.ssafy.altf4.entity.champion.Champion;
import com.ssafy.altf4.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChampionService {

    private final ChampionRepository championRepository;

    public Champion findById(Long id) {

        return championRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
