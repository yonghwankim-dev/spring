package hello.servlet.basic.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회 - Start");
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        // request.getParameter(paramName) paramName은 이름을 꺼낸다 (username, age)
        // request.getParameter("파라미터 이름")을 작성하면 값을 꺼낼 수 있다.
        System.out.println("전체 파라미터 조회 - End");
        System.out.println();
        System.out.println("단일 파라미터 조회 - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("단일 파라미터 조회 - end");
        System.out.println();
        System.out.println("이름이 같은 복수 파라미터 조회 - start");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        System.out.println("이름이 같은 복수 파라미터 조회 - end");
    }
}
