package com.yh.spring_autowired.book;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Getter
public class BookService implements BeanPostProcessor {
    // 주입 경우의 수 1 : @Primary 어노테이션
    // 주입 경우의 수 2 : 특정 빈이름으로 주입
    @Autowired
    @Qualifier("yongHwanBookRepository")
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 주입 경우의 수 3 : 해당 타입의 빈 모두 주입받기
    @Autowired
    List<BookRepository> bookRepositories;

    // 주입 방법 중 하나 : 이름을 맞춰서 주입
    @Autowired
    BookRepository myBookRepository;



    // 빈이 이미 등록된후 수행되는 처리기
    @PostConstruct
    public void setup(){
        System.out.println(myBookRepository.getClass());
    }
}
