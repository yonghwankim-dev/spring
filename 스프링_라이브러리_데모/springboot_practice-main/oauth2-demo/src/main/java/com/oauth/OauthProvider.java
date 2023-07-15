package com.oauth;

public class OauthProvider {
	private final String clientId;
	private final String clientSecret;
	private final String redirectUrl;
	private final String tokenUrl;
	private final String userInfoUrl;

	public OauthProvider(OauthProperties.User user, OauthProperties.Provider provider) {
		this(user.getClientId(), user.getClientSecret(), user.getRedirectUri(), provider.getTokenUri(),
			provider.getUserInfoUri());
	}

	public OauthProvider(String clientId, String clientSecret, String redirectUrl, String tokenUrl,
		String userInfoUrl) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
		this.tokenUrl = tokenUrl;
		this.userInfoUrl = userInfoUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public String getTokenUrl() {
		return tokenUrl;
	}

	public String getUserInfoUrl() {
		return userInfoUrl;
	}

	static class Builder {
		private String clientId;
		private String clientSecret;
		private String redirectUrl;
		private String tokenUrl;
		private String userInfoUrl;

		public Builder clientId(String clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder clientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
			return this;
		}

		public Builder redirectUrl(String redirectUrl) {
			this.redirectUrl = redirectUrl;
			return this;
		}

		public Builder tokenUrl(String tokenUrl) {
			this.tokenUrl = tokenUrl;
			return this;
		}

		public Builder userInfoUrl(String userInfoUrl) {
			this.userInfoUrl = userInfoUrl;
			return this;
		}

		public OauthProvider build() {
			return new OauthProvider(clientId, clientSecret, redirectUrl, tokenUrl, userInfoUrl);
		}
	}

	@Override
	public String toString() {
		return "OauthProvider{" +
			"clientId='" + clientId + '\'' +
			", clientSecret='" + clientSecret + '\'' +
			", redirectUrl='" + redirectUrl + '\'' +
			", tokenUrl='" + tokenUrl + '\'' +
			", userInfoUrl='" + userInfoUrl + '\'' +
			'}';
	}
}
