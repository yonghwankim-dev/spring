package kr.yh.spring_test.json_test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
@Getter
@Setter
@AllArgsConstructor
public class UserDetails {
    private Long id;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;
}