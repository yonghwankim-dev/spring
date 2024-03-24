package nemo.web_push;

import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class NotificationMessage {
	private String recipientToken;
	private String title;
	private String body;
	private String image;
	private Map<String, String> data;
}
