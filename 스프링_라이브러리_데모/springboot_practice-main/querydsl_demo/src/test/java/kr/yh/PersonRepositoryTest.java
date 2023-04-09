package kr.yh;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    public void cleanUp() throws Exception {
        repository.deleteAll();

        repository.save(new Person("철수", "김", 22, "kim@chulsu.com"));
        repository.save(new Person("차차", "김", 15, "cha@cha.cha"));
        repository.save(new Person("프링", "스", 41, "spring@bo.com"));
        repository.save(new Person("자바", "김", 38, "imjava@java.net"));
        repository.save(new Person("쿼리", "최", 19, "choi@que.com"));
        repository.save(new Person("고당", "최", 76, "best@good.com"));
        repository.save(new Person("선화", "스", 11, "flower@cha.cha"));
    }
    
    @Test
    public void 모든_리스트_가져오기() throws Exception {
        List<Person> personList = repository.findAll();
        assertThat(personList.size()).isEqualTo(7);
    }

    @Test
    public void 김씨_성_찾기(){
        //given
        JPAQueryFactory factory = new JPAQueryFactory(em);
        QPerson person = QPerson.person;
        int actual1 = factory.selectFrom(person).where(person.lastName.eq("김")).fetch().size();
        List<Person> actual2 = factory.selectFrom(person).where(person.lastName.eq("김"), person.email.like("%.com")).fetch();
        //when
        List<Person> actual3 = factory.selectFrom(person).where(person.lastName.eq("김"), person.age.between(20, 40)).fetch();
        //then
        assertThat(actual1).isEqualTo(3);
        assertThat(actual2.size()).isEqualTo(1);
        assertThat(actual3.size()).isEqualTo(2);
    }
    
    @Test
    public void 스선화_찾기(){
        //given
        String firstName = "선화";
        String lastName = "스";
        //when
        List<Person> actual1 = (List<Person>) repository.findAll(PersonPredicate.search(firstName, lastName));
        List<Person> actual2 = (List<Person>) repository.findAll(PersonPredicate.search(null, lastName));
        List<Person> actual3 = (List<Person>) repository.findAll(PersonPredicate.search(null, null));
        //then
        assertThat(actual1.size()).isEqualTo(1);
        assertThat(actual2.size()).isEqualTo(2);
        assertThat(actual3.size()).isEqualTo(7);
    }
}