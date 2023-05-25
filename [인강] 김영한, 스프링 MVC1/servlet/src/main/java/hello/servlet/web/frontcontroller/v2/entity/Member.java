package hello.servlet.web.frontcontroller.v2.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {

    private final String username;
    private final int age;
}
