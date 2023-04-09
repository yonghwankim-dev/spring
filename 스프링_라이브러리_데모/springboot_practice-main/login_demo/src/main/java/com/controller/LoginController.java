package com.controller;

import com.domain.Member;
import com.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login_process(String userid,
                                String password,
                                Model model,
                                HttpSession session){
        log.info("userid : " + userid);
        log.info("password : " + password);

        Member member = loginService.login(userid, password);

        if(member == null){
            model.addAttribute("error", "회원정보가 일치하지 않습니다.");
            return "login";
        }
        session.setAttribute("member", member);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
