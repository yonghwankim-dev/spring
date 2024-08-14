package nemo.listener.transactional_event_listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemberSignupMessageRepository {
	private List<String> store = new ArrayList<>();

	public void save(String message) {
		store.add(message);
	}

	public List<String> findAll() {
		return Collections.unmodifiableList(store);
	}

	public void deleteAll() {
		store.clear();
	}
}
