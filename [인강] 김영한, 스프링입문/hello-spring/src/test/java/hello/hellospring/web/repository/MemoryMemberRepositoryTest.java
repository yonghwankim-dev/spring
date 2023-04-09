package hello.hellospring.web.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    private MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("김용환");
        //when
        repository.save(member);
        //then
        Member actual = repository.findById(member.getId()).get();
        Assertions.assertThat(actual).isEqualTo(member);
    }

    @Test
    public void findByName() {
        //given
        Member member = new Member();
        member.setName("김용환");
        repository.save(member);
        //when
        Member actual = repository.findByName("김용환").get();
        //then
        Assertions.assertThat(actual).isEqualTo(member);
    }

    @Test
    public void findAll() {
        //given
        Member member = new Member();
        member.setName("김용환");
        repository.save(member);
        //when
        List<Member> actual = repository.findAll();
        //then
        Assertions.assertThat(actual.size()).isEqualTo(1);
    }

}
