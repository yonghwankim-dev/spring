package kr.yh;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.validation.ValidatorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final Validator validator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Event event = new Event();
        EventValidator eventValidator = new EventValidator();
        Errors errors = new BeanPropertyBindingResult(event, "event");

        eventValidator.validate(event, errors);
        System.out.println(errors.hasErrors());
        printErrors(errors);

        //////////////////////////////////////////////////////////////////////////////////
        Event event2 = new Event();
        event2.setLimit(-1);
        event2.setEmail("aaa");
        Errors errors2 = new BeanPropertyBindingResult(event2, "event2");

        System.out.println(validator.getClass());
        validator.validate(event2, errors2);
        System.out.println(errors2.hasErrors());
        printErrors(errors2);

    }

    private void printErrors(Errors errors) {
        for (ObjectError e : errors.getAllErrors()) {
            System.out.println("========Error Code=======");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        }
    }
}
