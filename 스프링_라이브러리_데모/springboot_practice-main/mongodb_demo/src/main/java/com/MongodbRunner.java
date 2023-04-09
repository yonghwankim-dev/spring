package com;

import com.account.Account;
import com.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongodbRunner implements ApplicationRunner {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account("yonghwan", "yonghwan@gmail.com");
        mongoTemplate.insert(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println(byId.get().getUsername());
        System.out.println(byId.get().getEmail());
    }
}
