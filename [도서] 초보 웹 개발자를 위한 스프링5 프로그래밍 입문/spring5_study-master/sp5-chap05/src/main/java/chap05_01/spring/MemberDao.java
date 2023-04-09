package chap05_01.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import chap05_01.config.ManualBean;

// 특이점
// 스프링이 검색해서 빈으로 등록가능하도록 @Component 애노테이션을 설정한다.
// ManualBean 애노테이션을 설정하여 컴포넌트 스캔 대상 제외
@ManualBean
@Component
public class MemberDao {

	private static long nextId = 0;

	private Map<String, Member> map = new HashMap<>();

	public Member selectByEmail(String email) {
		return map.get(email);
	}

	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}

	public void update(Member member) {
		map.put(member.getEmail(), member);
	}

	public Collection<Member> selectAll() {
		return map.values();
	}
}
