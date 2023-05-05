package hello.core.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MemoryMemberRepository implements MemberRepository {

    private static final Logger log = LoggerFactory.getLogger(MemoryMemberRepository.class);

    private static Map<Long, Member> store = new ConcurrentHashMap<>();

    public MemoryMemberRepository() {
        log.info("MemoryMemberRepository 객체 생성 호출");
    }

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }
}
