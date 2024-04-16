package com.ssafy.altf4.dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String puuid;

    private String gameName;

    private String tagLine;
}
