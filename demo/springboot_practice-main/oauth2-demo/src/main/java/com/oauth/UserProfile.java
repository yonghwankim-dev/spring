package com.oauth;

// OAuth 서버 별로 가져올 수 있는 유저 정보가 다릅니다.
// 일부 정보만 가져옵니다.
// token과 마찬가지로 유저 정보를 담을 수 있는 DTO 객체입니다.
public class UserProfile {
	private final String oauthId;
	private final String email;
	private final String name;
	private final String imageUrl;

	public UserProfile(String oauthId, String email, String name, String imageUrl) {
		this.oauthId = oauthId;
		this.email = email;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public String getOauthId() {
		return oauthId;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Member toMember() {
		return Member.builder()
			.oauthId(oauthId)
			.email(email)
			.name(name)
			.imageUrl(imageUrl)
			.role(Role.GUEST)
			.build();
	}

	public static Builder builder() {
		return new Builder();
	}

	static class Builder {
		private String oauthId;
		private String email;
		private String name;
		private String imageUrl;

		public Builder oauthId(String oauthId) {
			this.oauthId = oauthId;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder imageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public UserProfile build() {
			return new UserProfile(oauthId, email, name, imageUrl);
		}
	}
}
