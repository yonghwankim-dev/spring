package com.yh.spring_applicationcontext;

import com.yh.spring_applicationcontext.book.BookRepository;
import com.yh.spring_applicationcontext.book.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 설정 파일을 이용한 빈 설정 방법 1 : @Bean 어노테이션
@Configuration
public class ApplicationConfig {

    @Bean
    public BookRepository bookRepository(){
        return new BookRepository();
    }

    @Bean
    public BookService bookService(){
        return new BookService(bookRepository());
    }
}
