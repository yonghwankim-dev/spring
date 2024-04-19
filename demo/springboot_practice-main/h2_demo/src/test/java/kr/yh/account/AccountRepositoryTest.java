package kr.yh.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = {"local"})
public class AccountRepositoryTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;
    
    @Test
    public void di(){
        try(Connection connection = dataSource.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testSave(){
        //given
        Account account = new Account();
        account.setUsername("yonghwan");
        account.setPassword("pass");
        //when
        Account newAccount = accountRepository.save(account);
        //then
        assertThat(newAccount).isEqualTo(account);
    }

    @Test
    public void testFindByUsername(){
        //given
        Account account = new Account();
        account.setUsername("yonghwan");
        account.setPassword("pass");
        accountRepository.save(account);
        //when
        Optional<Account> foundAccount = accountRepository.findByUsername("yonghwan");
        //then
        assertThat(foundAccount).isNotEmpty();
        assertThat(foundAccount.get()).isEqualTo(account);
    }
}