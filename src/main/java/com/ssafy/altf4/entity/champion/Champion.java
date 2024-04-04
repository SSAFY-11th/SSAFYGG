package com.ssafy.altf4.entity.champion;

import com.ssafy.altf4.entity.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor @AllArgsConstructor
public class Champion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "champion_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private LineType line;

    private Double winRate;

    private String image;

    @OneToMany(mappedBy = "champion", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FavoriteChampion> favoriteChampions = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @Builder
    public Champion(String name, LineType line, Double winRate, String image) {
        this.name = name;
        this.line = line;
        this.winRate = winRate;
        this.image = image;
    }
}
