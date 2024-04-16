package com.ssafy.altf4.controller.openfeign;

import com.ssafy.altf4.dto.api.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account", url = "https://asia.api.riotgames.com/")
public interface RiotAccountClient {

    @GetMapping(value = "/riot/account/v1/accounts/by-puuid/{puuid}", consumes = "application/json")
    AccountDto getAccountInfo(@PathVariable("puuid") String puuid, @RequestParam("api_key") String key);
}

/**
 * localhost:8080/altf4/riot/apis/client/${puuid}?api_key=${api_key}
 */