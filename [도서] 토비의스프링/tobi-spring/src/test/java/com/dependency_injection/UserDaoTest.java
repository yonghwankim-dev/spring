package com.dependency_injection;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class UserDaoTest {

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        //given
        ApplicationContext ctx = new AnnotationConfigApplicationContext(UserDaoFactory.class);
        UserDao dao = ctx.getBean("userDao", UserDao.class);
        User user = new User("user", "백기선", "married");
        //when
        dao.add(user);
        User user2 = dao.get(user.getId());
        System.out.println(user2);
        //then
    }

}
