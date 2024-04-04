package com.ssafy.altf4.repository;

import com.ssafy.altf4.entity.champion.FavoriteChampion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteChampionRepository extends JpaRepository<FavoriteChampion, Long> {
}
