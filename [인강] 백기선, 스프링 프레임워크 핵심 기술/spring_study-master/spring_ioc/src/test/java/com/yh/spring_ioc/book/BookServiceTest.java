package com.yh.spring_ioc.book;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Test
    public void test() throws Exception{
        //given
        Book book = new Book();
        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);

        //when
        Book result = bookService.save(book);

        //then
        assertThat(result).isNull();
    }

}