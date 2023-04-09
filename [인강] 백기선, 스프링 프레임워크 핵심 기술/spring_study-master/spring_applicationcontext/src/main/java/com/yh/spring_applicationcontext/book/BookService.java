package com.yh.spring_applicationcontext.book;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class BookService {

    // 주입 방법3 : @Autowired 어노테이션
    @Autowired
    private BookRepository bookRepository;

    // 주입 방법1 : 생성자
    public BookService(){}
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 주입 방법2 : setter 메서드
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}