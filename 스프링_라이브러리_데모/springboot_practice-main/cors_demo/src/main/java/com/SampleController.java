package com;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = {"http://localhost:18080"}) // 해당 컨트롤러 내에 메서드 전체에 적용
@RequestMapping("/sample")
public class SampleController {
    @CrossOrigin(origins = {"http://localhost:18080"})
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
