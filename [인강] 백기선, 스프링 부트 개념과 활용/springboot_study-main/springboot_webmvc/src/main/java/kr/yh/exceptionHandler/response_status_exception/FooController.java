package kr.yh.exceptionHandler.response_status_exception;

import kr.yh.exceptionHandler.response_status_exception_resolver.MyResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class FooController {
    private final FooService service;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            Foo resourceById = RestPreconditions.checkFound(service.findOne(id));

            eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
            return resourceById;
        }
        catch (MyResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);
        }
    }
}
