package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.entity.Member;
import hello.servlet.web.frontcontroller.v4.repository.MemberRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members";
    }
}
