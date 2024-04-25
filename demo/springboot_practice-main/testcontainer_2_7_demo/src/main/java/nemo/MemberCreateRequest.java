package nemo;

public class MemberCreateRequest {
	private String name;

	public Member toEntity() {
		return new Member(name);
	}
}
