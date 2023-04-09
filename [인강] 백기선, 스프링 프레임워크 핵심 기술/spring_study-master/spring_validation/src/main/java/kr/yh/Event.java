package kr.yh;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class Event {
    Integer id;

    @NotEmpty
    String  title;

    @Min(0)
    @NotNull
    Integer limit;

    @Email
    String email;

}
