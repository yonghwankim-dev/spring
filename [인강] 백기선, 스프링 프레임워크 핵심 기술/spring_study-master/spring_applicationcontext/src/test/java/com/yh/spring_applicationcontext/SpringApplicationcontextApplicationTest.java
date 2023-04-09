package com.yh.spring_applicationcontext;


import com.yh.spring_applicationcontext.book.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
class SpringApplicationcontextApplicationTest {

    @Test
    public void givenClassPathXmlApplicationContextUsingBean_whenGetBeanBookService_thenBookRepositoryIsNotNull() throws Exception{
        //given
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        //when
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        BookService bookService = (BookService) context.getBean("bookService");

        //then
        assertThat(beanDefinitionNames).contains("bookService", "bookRepository");
        assertThat(bookService.getBookRepository()).isNotNull();
    }

    @Test
    public void givenClassPathXmlApplicationContextUsingComponentScan_whenGetBeanBookService_thenBookRepositoryIsNotNull() throws Exception{
        //given
        ApplicationContext context = new ClassPathXmlApplicationContext("application2.xml");

        //when
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        BookService bookService = (BookService) context.getBean("bookService");

        //then
        assertThat(beanDefinitionNames).contains("bookService", "bookRepository");
        assertThat(bookService.getBookRepository()).isNotNull();
    }

    @Test
    public void givenAnnotationConfigApplicationContextUsingBeanAnnotation_whenGetBookService_thenBookRepositoryIsNotNull() throws Exception{
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        //when
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        BookService bookService = (BookService) context.getBean("bookService");

        //then
        assertThat(beanDefinitionNames).contains("bookService", "bookRepository");
        assertThat(bookService.getBookRepository()).isNotNull();
    }

    @Test
    public void givenAnnotationConfigApplicationContextUsingComponentAnnotation_whenGetBookService_thenBookRepositoryIsNotNull() throws Exception{
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig2.class);

        //when
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        BookService bookService = (BookService) context.getBean("bookService");

        //then
        assertThat(beanDefinitionNames).contains("bookService", "bookRepository");
        assertThat(bookService.getBookRepository()).isNotNull();
    }
}