package nemo.sse.repository;

import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {
	SseEmitter save(String emitterId, SseEmitter sseEmitter);
	void saveEventCache(String eventCacheId, Object event);
	Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId);
	Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId);
	void deleteById(String id);
	void deleteAllEmitterStartWithId(String memberId);
	void deleteAllEventCacheStartWithId(String memberId);
}
