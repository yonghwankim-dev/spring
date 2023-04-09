package com.service;

import com.domain.Member;
import com.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String userid, String password){
        Member member = memberRepository.findByUserid(userid).orElse(null);
        if(member != null && member.equalUseridAndPassword(userid, password)){
            return member;
        }
        return null;
    }
}
