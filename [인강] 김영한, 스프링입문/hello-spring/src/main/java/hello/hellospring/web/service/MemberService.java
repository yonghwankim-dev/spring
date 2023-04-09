package hello.hellospring.web.service;

import hello.hellospring.domain.Member;
import hello.hellospring.web.repository.MemberRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) throws SQLException {
        // 중복 회원이 있으면 안된다
        validateDuplicatedMember(member);

        repository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        Optional<Member> result = repository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> fineOne(Long memberId) {
        return repository.findById(memberId);
    }

}
