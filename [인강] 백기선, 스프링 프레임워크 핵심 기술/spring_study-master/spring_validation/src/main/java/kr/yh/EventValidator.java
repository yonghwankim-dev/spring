package kr.yh;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                                            "title",
                                        "notempty",
                                    "Empty title is not allowed");

        // errors 인스턴스에 직접 에러 작성
        Event event = (Event) target;
        if(event.getTitle() == null){
            errors.reject("notempty", "Empty title is not allowed");
            errors.rejectValue("title", "notempty", "Empty title is not allowed");
        }

    }
}
