package kr.yh;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(messageSource.getClass());
        System.out.println(messageSource.getMessage("greeting", new String[]{"yonghwan"}, Locale.getDefault()));
        System.out.println(messageSource.getMessage("greeting", new String[]{"yonghwan"}, Locale.US));
        System.out.println(messageSource.getMessage("greeting", new String[]{"yonghwan"}, Locale.KOREA));
        System.out.println();

        while(true){
            System.out.println(messageSource.getMessage("greeting", new String[]{"yonghwan"}, Locale.US));
            System.out.println(messageSource.getMessage("greeting", new String[]{"yonghwan"}, Locale.KOREA));
            System.out.println();
            Thread.sleep(1000);
        }
    }
}
