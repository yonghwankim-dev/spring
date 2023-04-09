package kr.yh.external_config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("yonghwan")
@Validated
@Component
@Getter
@Setter
public class YonghwanProperties {
    @NotEmpty
    private String name;
    private int age;
    private String fullName;
    private String dbName;

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout = Duration.ofSeconds(30);
}
