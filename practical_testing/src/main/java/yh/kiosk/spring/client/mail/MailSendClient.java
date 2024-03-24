package yh.kiosk.spring.client.mail;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MailSendClient {

	public boolean sendEmail(String fromEmail, String toEmail, String subject, String content) {
		// 메일 전송
		log.info("메일 전송");
		throw new IllegalArgumentException("메일 전송");
	}

	public void a() {
		log.info("a");
	}

	public void b() {
		log.info("b");
	}

	public void c() {
		log.info("c");
	}
}
