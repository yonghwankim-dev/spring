package kr.yh.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/helloUser")
    public String hello(){
        return "hello";
    }

    @PostMapping("/users/create")
    public User create(@RequestBody User user){
        return user;
    }
}
