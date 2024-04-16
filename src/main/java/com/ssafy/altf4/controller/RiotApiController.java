package com.ssafy.altf4.controller;

import com.ssafy.altf4.controller.openfeign.RiotAccountClient;
import com.ssafy.altf4.dto.api.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/altf4/riot/apis")
@RequiredArgsConstructor
public class RiotApiController {

    private final RiotAccountClient client;

    @GetMapping("/client/{puuid}")
    public ResponseEntity<AccountDto> findAccountByPuuid(@PathVariable("puuid") String puuid, @RequestParam("api_key") String key){

        AccountDto accountInfo = client.getAccountInfo(puuid, key);

        return ResponseEntity.ok(accountInfo);
    }
}
