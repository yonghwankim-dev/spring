package kr.yh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class AppRunner implements ApplicationRunner {
    @Qualifier("webApplicationContext")
    @Autowired
    ResourceLoader resourceLoader;

    // ApplicationContext는 ResourceLoader를 상속받았기 때문에 getResource로 자원 접근이 가능함
    @Qualifier("webApplicationContext")
    @Autowired
    ApplicationContext applicationContext;



    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Resource resource1 = resourceLoader.getResource("classpath:test.txt");
//        Resource resource2 = applicationContext.getResource("classpath:test.txt");

//        System.out.println(resource1.exists()); // true
//        System.out.println(resource2.exists()); // true
//
//        String s = Files.readString(Path.of(resource1.getURI()));
//        System.out.println(s); // hello test.txt
//
//        // applicationContext의 타입 확인
//        Resource resource3 = applicationContext.getResource("classpath:test.txt");
//        System.out.println(applicationContext.getClass()); // class org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
//        System.out.println(resource3); // class path resource [test.txt]
//
//        // classpath 접두어 없이 리소스 타입 확인
//        Resource resource4 = applicationContext.getResource("test.txt");
//        System.out.println(resource4);          // ServletContext resource [/test.txt]
//        System.out.println(resource4.exists()); // false
    }
}
