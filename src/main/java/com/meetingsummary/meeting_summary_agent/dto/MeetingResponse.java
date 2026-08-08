package com.meetingsummary.meeting_summary_agent.dto;

import java.time.LocalDateTime;

public class MeetingResponse {
	
	
	private Long id;
	
	private String message;
	
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public MeetingResponse(Long id, String message, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.message = message;
		this.createdAt = createdAt;
	}

}
