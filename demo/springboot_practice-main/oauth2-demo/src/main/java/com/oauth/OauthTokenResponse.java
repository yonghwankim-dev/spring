package com.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

// OAuth 서버와의 통신을 통해 access token을 받아 저장하는 DTO 객체
public class OauthTokenResponse {
	@JsonProperty("access_token")
	private String accessToken;

	private String scope;

	@JsonProperty("token_type")
	private String tokenType;

	public OauthTokenResponse() {
	}

	public OauthTokenResponse(String accessToken, String scope, String tokenType) {
		this.accessToken = accessToken;
		this.scope = scope;
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getScope() {
		return scope;
	}

	public String getTokenType() {
		return tokenType;
	}

	public static class Builder {
		private String accessToken;
		private String scope;
		private String tokenType;

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder scope(String scope) {
			this.scope = scope;
			return this;
		}

		public Builder tokenType(String tokenType) {
			this.tokenType = tokenType;
			return this;
		}

		public OauthTokenResponse build() {
			return new OauthTokenResponse(accessToken, scope, tokenType);
		}
	}
}
