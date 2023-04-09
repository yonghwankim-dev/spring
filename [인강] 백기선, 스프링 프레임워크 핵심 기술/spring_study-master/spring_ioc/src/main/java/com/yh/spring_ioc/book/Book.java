package com.yh.spring_ioc.book;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Book {
    private Date created;
    private BookStatus bookStatus;
}
