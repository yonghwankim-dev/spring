package kr.yh.hateoas;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/hateoas")
public class HateoasSampleController {

    @GetMapping
    public EntityModel<Hello> hello() {
        Hello hello = new Hello("Hey,","yonghwan");

        // HATEOAS
        EntityModel<Hello> resource = EntityModel.of(hello);

        // 이 hello값을 반환시킬때 hello가 사용할 수 있는 추가 정보를 하이퍼링크로 넣어놓을 예정
        Link link = linkTo(methodOn(this.getClass()).hello()).withSelfRel();
        resource.add(link);
        return resource;
    }
}
