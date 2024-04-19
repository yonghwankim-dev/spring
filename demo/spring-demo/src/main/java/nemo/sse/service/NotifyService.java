package nemo.sse.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import nemo.sse.entity.Member;
import nemo.sse.entity.Notify;
import nemo.sse.repository.EmitterRepository;
import nemo.sse.repository.NotifyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotifyService {
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	private final EmitterRepository emitterRepository;
	private final NotifyRepository notifyRepository;

	public SseEmitter subscribe(String username, String lastEventId) {
		String emitterId = makeTimeIncludeId(username);
		SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

		emitter.onCompletion(()->emitterRepository.deleteById(emitterId));
		emitter.onTimeout(()->emitterRepository.deleteById(emitterId));

		String eventId = makeTimeIncludeId(username);
		sendNotification(emitter, eventId, emitterId, String.format("EventStream Created. [userEmail=%s]", username));

		if (hasLostData(lastEventId)){
			sendLostData(lastEventId, username, emitterId, emitter);
		}
		return emitter;
	}

	private String makeTimeIncludeId(String email) {
		return String.format("%s_%d", email, System.currentTimeMillis());
	}

	private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
		try {
			emitter.send(SseEmitter.event()
				.id(eventId)
				.name("sse")
				.data(data));
		} catch (IOException e) {
			emitterRepository.deleteById(emitterId);
		}
	}

	private boolean hasLostData(String lastEventId) {
		return !lastEventId.isEmpty();
	}

	private void sendLostData(String lastEventId, String userEmail, String emitterId, SseEmitter emitter) {
		Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByMemberId(
			userEmail);
		eventCaches.entrySet().stream()
			.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
			.forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
	}

	public void send(Member receiver, Notify.NotificationType notificationType, String content, String url){
		Notify notification = notifyRepository.save(createNotification(receiver, notificationType, content, url));

		String receiverEmail = receiver.getEmail();
		String eventId = String.format("%s_%d", receiverEmail, System.currentTimeMillis());
		Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(
			receiverEmail);
		emitters.forEach((key, emitter)->{
			emitterRepository.saveEventCache(key, notification);
			sendNotification(emitter, eventId, key, NotifyDto.Response.createResponse(notification));
		});
	}

	private Notify createNotification(Member receiver, Notify.NotificationType notificationType, String content, String url) {
		return Notify.builder()
			.receiver(receiver)
			.notificationType(notificationType)
			.content(content)
			.url(url)
			.isRead(false)
			.build();
	}
}
