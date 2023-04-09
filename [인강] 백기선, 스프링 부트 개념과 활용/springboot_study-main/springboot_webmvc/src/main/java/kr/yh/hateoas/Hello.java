package kr.yh.hateoas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class Hello {
    private String prefix;
    private String name;

    @Override
    public String toString() {
        return prefix + " " + name;
    }
}
