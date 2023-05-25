package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelAndView process(Map<String, String> paramMap) throws ServletException, IOException {
        String viewPath = "new-form";
        return new ModelAndView(viewPath);
    }
}
