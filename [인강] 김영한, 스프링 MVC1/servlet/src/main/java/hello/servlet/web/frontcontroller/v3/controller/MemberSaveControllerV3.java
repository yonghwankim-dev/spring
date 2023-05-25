package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.entity.Member;
import hello.servlet.web.frontcontroller.v3.repository.MemberRepository;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelAndView process(Map<String, String> paramMap) throws ServletException, IOException {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
