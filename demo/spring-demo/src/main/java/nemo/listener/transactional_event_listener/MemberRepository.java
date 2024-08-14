package nemo.listener.transactional_event_listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
	private Map<String, Member> store = new ConcurrentHashMap<>();

	public void save(Member member) {
		store.put(member.getName(), member);
	}

	public Member findByName(String name) {
		return store.get(name);
	}

	public void deleteAll() {
		store.clear();
	}
}
