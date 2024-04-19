package com.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;


@DataMongoTest(properties = "spring.mongodb.embedded.version=3.4.11")
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;
    
    @Test
    public void findByEmail(){
        //given
        Account account = new Account("kim", "kim@gmail.com");
        accountRepository.save(account);
        //when
        Optional<Account> byId = accountRepository.findById(account.getId());
        Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());
        //then
        Assertions.assertThat(byId.get().getUsername()).isEqualTo("kim");
        Assertions.assertThat(byEmail.get().getEmail()).isEqualTo("kim@gmail.com");
    }
}