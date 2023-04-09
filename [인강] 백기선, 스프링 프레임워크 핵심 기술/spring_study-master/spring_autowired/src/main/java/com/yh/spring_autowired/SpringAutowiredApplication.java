package com.yh.spring_autowired;

import com.yh.out.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Supplier;

@SpringBootApplication
public class SpringAutowiredApplication {

    // Component 스캔에 탐색되지 않았기 때문에 주입이 불가능함
    @Autowired
    MyService myService;

    public static void main(String[] args) {
        //SpringApplication.run(SpringAutowiredApplication.class, args);

        var app = new SpringApplication(SpringAutowiredApplication.class);
        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx ->{
                    ctx.registerBean(MyService.class);
                    ctx.registerBean(ApplicationRunner.class, () -> args1 -> System.out.println("Functional Bean Definition!!"));
                } );

        app.run(args);

    }

}
