package com.yh.basic;


import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TomcatTest {
    @Test
    public void tomcatTest() throws Exception{
        //given
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        Context context = tomcat.addContext("/", "/");

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("<html><head><title>");
                writer.println("Hello, Tomcat Title");
                writer.println("</title></head>");
                writer.println("<body><h1>Hello Tomcat</h1></body>");
                writer.println("</html>");
            }
        };
        String servletName = "helloServlet";
        tomcat.addServlet("/", servletName, servlet);
        context.addServletMappingDecoded("/hello", servletName);

        tomcat.start();
//        tomcat.getServer().await();
        //when
        
        //then
    }
}
