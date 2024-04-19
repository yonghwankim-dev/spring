package nemo.sse.service;

import nemo.sse.entity.Notify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class NotifyDto {
	@AllArgsConstructor
	@Builder
	@NoArgsConstructor
	@Getter
	@Setter
	public static class Response {
		String id;
		String name;
		String content;
		String type;
		String createdAt;
		public static Response createResponse(Notify notify) {
			return Response.builder()
				.content(notify.getContent())
				.id(notify.getId().toString())
				.name(notify.getReceiver().getName())
				.createdAt(notify.getCreateAt().toString())
				.build();
		}
	}
}
