package nemo.listener.event_listener;

import jakarta.persistence.Entity;


public class Member {
	private String name;

	public Member(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
