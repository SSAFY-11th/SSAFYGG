package com.ssafy.altf4.repository;

import com.ssafy.altf4.entity.champion.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Long> {

}
