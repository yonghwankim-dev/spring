package kr.yh.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test") // 프로파일 정의 방법 3
//@Profile("!prod") // 프로파일 설정이 prod가 아닌 경우에 이 빈을 활성화시킴
//@Profile("!prod && test") // 논리식 사용 가능
public class BookRepository {
}
