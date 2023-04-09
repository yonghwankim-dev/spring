package com.yh.spring_autowired;

import com.yh.spring_autowired.book.BookRepository;
import com.yh.spring_autowired.book.BookService;
import com.yh.spring_autowired.book.YongHwanBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SpringAutowiredApplicationTests {

    @Autowired
    BookService bookService;
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() throws Exception{
        //given

        //when

        //then
        for (BookRepository bookRepository : bookService.getBookRepositories()) {
            System.out.println(bookRepository.getClass());
        }
    }

    @Test
    public void test2() throws Exception{
        //given

        //when
        Class<YongHwanBookRepository> repositoryClass = (Class<YongHwanBookRepository>) bookService.getBookRepository().getClass();

        //then
        assertThat(repositoryClass).isEqualTo(YongHwanBookRepository.class);

    }
    
    @Test
    public void AutowiredAnnotationBeanPostProcessor_빈등록확인() throws Exception{
        //given
        AutowiredAnnotationBeanPostProcessor bean = applicationContext.getBean(AutowiredAnnotationBeanPostProcessor.class);

        //when
        
        //then
        assertThat(bean).isNotNull();
    }

}
