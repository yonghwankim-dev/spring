package com.yh.spring_autowired.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary // 동일한 타입(BookRepository)의 빈이 여러개인 경우 해당 빈 사용 
public class YongHwanBookRepository implements BookRepository{
}
