package com.ssafy.altf4.service;

import com.ssafy.altf4.entity.champion.Champion;
import com.ssafy.altf4.entity.member.Member;

import java.util.List;

public class TestServiceImpl implements TestService {
    private static TestService service = new TestServiceImpl();

    // 1. 객체는 한 번만 생성이 되어야 함
    // 2. 다른 곳에서 객체를 만들 수 없어야 함
    private TestServiceImpl() {}

    public static TestService getInstance() {

        return service;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
