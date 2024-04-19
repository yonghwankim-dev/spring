package nemo.web_push;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationRestController {
	private final FirebaseMessagingService firebaseMessagingService;

	@PostMapping
	public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage){
		return firebaseMessagingService.sendNotificationByToken(notificationMessage);
	}
}
