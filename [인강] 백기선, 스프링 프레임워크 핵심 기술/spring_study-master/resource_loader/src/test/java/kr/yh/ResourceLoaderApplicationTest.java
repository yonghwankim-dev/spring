package kr.yh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
public class ResourceLoaderApplicationTest {
    @Qualifier("webApplicationContext")
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private ClassPathXmlApplicationContext classPathCtx;
    @Autowired
    private FileSystemXmlApplicationContext fileSystemCtx;
    @Autowired
    private WebApplicationContext webCtx;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void getResource(){
        //given
        String location = "test.txt";
        //when
        Resource res1 = ctx.getResource(location);
        Resource res2 = classPathCtx.getResource(location);
        Resource res3 = fileSystemCtx.getResource(location);
        Resource res4 = webCtx.getResource(location);
        //then
        System.out.println("res1 : " + res1);
        System.out.println("res2 : " + res2);
        System.out.println("res3 : " + res3);
        System.out.println("res4 : " + res4);
    }

    @Test
    public void getResource_withPrefix_classpath(){
        //given
        String location = "classpath:test.txt";
        //when
        Resource res1 = ctx.getResource(location);
        Resource res2 = classPathCtx.getResource(location);
        Resource res3 = fileSystemCtx.getResource(location);
        Resource res4 = webCtx.getResource(location);
        //then
        System.out.println("res1 : " + res1);
        System.out.println("res2 : " + res2);
        System.out.println("res3 : " + res3);
        System.out.println("res4 : " + res4);
    }

    @Test
    public void getResource_withPrefix_file(){
        //given
        String location = "file:test.txt";
        //when
        Resource res1 = ctx.getResource(location);
        Resource res2 = classPathCtx.getResource(location);
        Resource res3 = fileSystemCtx.getResource(location);
        Resource res4 = webCtx.getResource(location);
        //then
        System.out.println("res1 : " + res1);
        System.out.println("res2 : " + res2);
        System.out.println("res3 : " + res3);
        System.out.println("res4 : " + res4);
    }

    @Test
    public void getResource_withPrefix_https(){
        //given
        String location = "https:test.txt";
        //when
        System.out.println(ctx);
        Resource res1 = ctx.getResource(location);
        Resource res2 = classPathCtx.getResource(location);
        Resource res3 = fileSystemCtx.getResource(location);
        Resource res4 = webCtx.getResource(location);
        //then
        System.out.println("res1 : " + res1);
        System.out.println("res2 : " + res2);
        System.out.println("res3 : " + res3);
        System.out.println("res4 : " + res4);
    }
    
    @Test
    public void getResourcesTest() throws IOException {
        Resource[] resources = ctx.getResources("classpath*:/test*.txt");// test 이름을 시작하는 txt파일들을 가져옴
        for(Resource resource : resources){
            String content = Files.readString(Path.of(resource.getURI()));
            System.out.println(content);
        }
    }

    @Test
    public void resourceLoaderAwareTest() throws IOException {
        //given
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        //when
        ResourceLoaderAwareImpl resourceLoaderAware = ctx.getBean(ResourceLoaderAwareImpl.class);
        Resource resource = resourceLoaderAware.getResource("test.txt");
        String content = Files.readString(Path.of(resource.getURI()));
        //then
        System.out.println(content);

    }

    @Test
    public void resourceLoaderAware_Without_Test() throws IOException {
        //given
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        //when
        ResourceLoaderService resourceLoaderAware = ctx.getBean(ResourceLoaderService.class);
        Resource resource = resourceLoaderAware.getResource("test.txt");
        String content = Files.readString(Path.of(resource.getURI()));
        //then
        System.out.println(content);
    }
}