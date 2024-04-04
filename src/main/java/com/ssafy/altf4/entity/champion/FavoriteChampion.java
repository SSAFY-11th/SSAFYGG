package com.ssafy.altf4.entity.champion;

import com.ssafy.altf4.entity.member.Member;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class FavoriteChampion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_champ_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champion_id")
    private Champion champion;

    @Builder
    public FavoriteChampion(Member member, Champion champion) {
        this.member = member;
        this.champion = champion;
    }
}
