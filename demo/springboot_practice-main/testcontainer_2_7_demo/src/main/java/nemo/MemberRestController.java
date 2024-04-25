package nemo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberRepository repository;

    @PostMapping
    public Member save(@RequestBody MemberCreateRequest request) {
        return repository.save(request.toEntity());
    }

    @GetMapping
    public List<Member> findAll() {
        return repository.findAll();
    }
}
