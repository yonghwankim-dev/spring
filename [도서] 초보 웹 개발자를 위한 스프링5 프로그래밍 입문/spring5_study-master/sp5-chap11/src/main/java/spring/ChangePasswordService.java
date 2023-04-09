package spring;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	// 트랜잭션 롤백은 RuntimeException 클래스와 자식 클래스에게만 롤백이 수행됨
	// SQLException과 같은 클래스는 자식 클래스가 아니므로 롤백이 수행되지 않음
	// 이를 해결하기 위해 @Transactional 애노테이션 설정 옵션에 rollbackFor 옵션에 SQLException 클래스를 설정한다.
	@Transactional(rollbackFor = SQLException.class)
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
