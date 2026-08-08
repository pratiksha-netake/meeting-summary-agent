package com.meetingsummary.meeting_summary_agent.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="meeting")
public class Meeting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	private String title;
	
	@Lob
	private String content;
	
	private String type;
	
	private String fileName;
	
	private String fileType;
	
	private LocalDateTime createdAt;
	
	public Meeting() {}

	public Meeting(Long id, String title, String content, String type, String fileName, String fileType,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.type = type;
		this.fileName = fileName;
		this.fileType = fileType;
		this.createdAt = createdAt;
	}
	
	
	public Meeting(
            String title,
            String content,
            String type
    ){

        this.title = title;
        this.content = content;
        this.type = type;
        this.createdAt = LocalDateTime.now();

    }
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
