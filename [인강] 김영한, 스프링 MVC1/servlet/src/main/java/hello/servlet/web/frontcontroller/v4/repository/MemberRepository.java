package hello.servlet.web.frontcontroller.v4.repository;

import hello.servlet.web.frontcontroller.v4.entity.Member;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemberRepository {

    private static Map<String, Member> store = new ConcurrentHashMap<>();

    private static MemberRepository instance;

    private MemberRepository() {

    }

    public static MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
            return instance;
        }
        return instance;
    }

    public void save(Member member) {
        store.put(member.getUsername(), member);
    }

    public List<Member> findAll() {
        return store.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
