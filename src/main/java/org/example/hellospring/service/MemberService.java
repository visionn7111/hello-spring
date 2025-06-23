package org.example.hellospring.service;

import org.example.hellospring.repository.MemberRepository;
import org.example.hellospring.domain.Member;
import org.example.hellospring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService {
    
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    // 회원 가입
    
    public Long join(Member member){
        
        // 같은 이름이 있는지 중복 확인
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

}
