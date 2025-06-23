package org.example.hellospring.service;

import org.example.hellospring.repository.JpaMemberRepository;
import org.example.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager; // 올바른 import 추가

@Configuration
public class Springconfig { // 클래스명과 생성자명 일치

    private final EntityManager em;

    @Autowired
    public Springconfig(EntityManager em) { // 대소문자 일치
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}