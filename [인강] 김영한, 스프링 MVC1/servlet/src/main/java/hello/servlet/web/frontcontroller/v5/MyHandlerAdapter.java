package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelAndView;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws ServletException, IOException;
}
