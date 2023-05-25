package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }

}
