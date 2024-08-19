package com.nemo.rabbitmq;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MessageDto {
	private String message;

	@Override
	public String toString() {
		return String.format("message=%s", message);
	}
}
