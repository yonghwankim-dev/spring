package kr.yh;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
@AllArgsConstructor
public class EventFormatter implements Formatter<Event> {

    private final MessageSource messageSource;

    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        return new Event(Integer.parseInt(text));
    }

    @Override
    public String print(Event object, Locale locale) {
        messageSource.getMessage("title", null, locale);
        return object.getId().toString();
    }
}
