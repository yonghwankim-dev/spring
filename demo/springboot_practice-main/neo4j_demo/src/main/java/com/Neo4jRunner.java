package com;


import org.neo4j.driver.internal.SessionFactory;
import org.neo4j.driver.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Neo4jRunner implements ApplicationRunner {
    @Autowired
    Neo4jTemplate neo4jTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) {
        Account account = new Account("kim", "kim@gmail.com");
        Role role = new Role("admin");
        account.getRoles().add(role);

        Account save = accountRepository.save(account);
        System.out.println(save.getUsername());
        System.out.println(save.getEmail());
        System.out.println(save.getRoles());
    }
}
