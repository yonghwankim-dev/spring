package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.entity.Member;
import hello.servlet.web.frontcontroller.v3.repository.MemberRepository;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelAndView process(Map<String, String> paramMap) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.getModel().put("members", members);
        return mv;
    }
}
