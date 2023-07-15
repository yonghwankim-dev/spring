package com.oauth;

public class Member {
	private Long id;
	private String oauthId;
	private String email;
	private String name;
	private String imageUrl;
	private Role role;

	public Member() {
	}

	public Member(String oauthId, String email, String name, String imageUrl, Role role) {
		this(null, oauthId, email, name, imageUrl, role);
	}

	public Member(Long id, String oauthId, String email, String name, String imageUrl, Role role) {
		this.id = id;
		this.oauthId = oauthId;
		this.email = email;
		this.name = name;
		this.imageUrl = imageUrl;
		this.role = role;
	}

	public Member update(String name, String email, String imageUrl) {
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}

	public Long getId() {
		return id;
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

	public Role getRole() {
		return role;
	}

	public static Builder builder() {
		return new Builder();
	}

	static class Builder {
		private Long id;
		private String oauthId;
		private String email;
		private String name;
		private String imageUrl;
		private Role role;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

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

		public Builder role(Role role) {
			this.role = role;
			return this;
		}

		public Member build() {
			return new Member(id, oauthId, email, name, imageUrl, role);
		}
	}
}
