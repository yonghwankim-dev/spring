package com;

import com.account.Account;
import com.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) {
        Account account = accountService.createAccount("yonghwan", "1234");
        System.out.println(account);
    }
}
