package jpabook.japshop.service;

import jpabook.japshop.domain.Member;
import jpabook.japshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional // 쓰기연산시 @Transactional 애노테이션을 추가, 우선권을 가짐
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원검증
     */
    public void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 특정회원조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
