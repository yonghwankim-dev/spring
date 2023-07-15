package com.oauth;

public class LoginResponse {
	private Long id;
	private String name;
	private String email;
	private String imageUrl;
	private Role role;
	private String tokenType;
	private String accessToken;
	private String refreshToken;

	public LoginResponse() {
	}

	public LoginResponse(Long id, String name, String email, String imageUrl, Role role, String tokenType,
		String accessToken, String refreshToken) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.role = role;
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Role getRole() {
		return role;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private String email;
		private String imageUrl;
		private Role role;
		private String tokenType;
		private String accessToken;
		private String refreshToken;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
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

		public Builder tokenType(String tokenType) {
			this.tokenType = tokenType;
			return this;
		}

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder refreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		public LoginResponse build() {
			return new LoginResponse(id, name, email, imageUrl, role, tokenType, accessToken, refreshToken);
		}
	}
}
