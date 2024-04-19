package com.oauth;

import java.util.Arrays;
import java.util.Map;

// 얻어온 유저 정보(map)를 UserProfile로 만들어 줘야 하는데
// OAuth 서버 별로 데이터의 key값이 다릅니다. 예를 들어 github 프로필 이미지는 avatar_url이지만
// 구글의 경우 picture입니다. 따라서 OAuth 서버가 어떤 형식으로 데이터를 리턴하는지 확인해보고 추가해주어야 합니다.
public enum OauthAttributes {
	GITHUB("github") {
		@Override
		public UserProfile of(Map<String, Object> attributes) {
			return UserProfile.builder()
				.oauthId(String.valueOf(attributes.get("id")))
				.email((String)attributes.get("email"))
				.name((String)attributes.get("name"))
				.imageUrl((String)attributes.get("avatar_url"))
				.build();
		}
	};

	private final String providerName;

	OauthAttributes(String providerName) {
		this.providerName = providerName;
	}

	public static UserProfile extract(String providerName, Map<String, Object> attributes) {
		return Arrays.stream(values())
			.filter(provider -> providerName.equals(provider.providerName))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new)
			.of(attributes);
	}

	public abstract UserProfile of(Map<String, Object> attributes);
}
