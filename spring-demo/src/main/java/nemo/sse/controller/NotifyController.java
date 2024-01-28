package nemo.sse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import nemo.sse.auth.AuthMember;
import nemo.sse.auth.AuthenticationPrincipal;
import nemo.sse.service.NotifyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notify")
@RequiredArgsConstructor
public class NotifyController {
	private final NotifyService notifyService;

	@GetMapping(value = "/subscribe", produces = "text/event-stream")
	public SseEmitter subscribe(@AuthenticationPrincipal AuthMember principal,
		@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
		return notifyService.subscribe(principal.getUsername(), lastEventId);
	}
}
