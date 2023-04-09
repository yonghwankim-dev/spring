package kr.yh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextConfiguration {
    @Bean
    public ClassPathXmlApplicationContext classPathXmlApplicationContext(){
        return new ClassPathXmlApplicationContext();
    }

    @Bean
    public FileSystemXmlApplicationContext fileSystemXmlApplicationContext(){
        return new FileSystemXmlApplicationContext();
    }

    @Bean
    public ResourceLoaderAwareImpl resourceLoaderAwareImpl(){
        ResourceLoaderAwareImpl resourceLoaderAwareImpl = new ResourceLoaderAwareImpl();
        resourceLoaderAwareImpl.setResourceLoader(new ClassPathXmlApplicationContext());
        return resourceLoaderAwareImpl;
    }

    @Bean
    public ResourceLoaderService resourceLoaderAwareImpl2(){
        return new ResourceLoaderService(new ClassPathXmlApplicationContext());
    }
}
