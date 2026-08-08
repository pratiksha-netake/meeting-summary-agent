package com.meetingsummary.meeting_summary_agent.dto;

import jakarta.validation.constraints.NotBlank;

public class MeetingRequest {
	
	@NotBlank(message="Title is required")
	private String title;
	
	@NotBlank(message="Meeting content is required")
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
