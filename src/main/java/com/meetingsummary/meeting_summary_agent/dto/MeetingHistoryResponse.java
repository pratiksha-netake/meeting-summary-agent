package com.meetingsummary.meeting_summary_agent.dto;

import java.time.LocalDateTime;


public class MeetingHistoryResponse {


    private Long id;

    private String title;

    private String type;

    private String message;

    private LocalDateTime createdAt;



    public MeetingHistoryResponse(
            Long id,
            String title,
            String type,
            String message,
            LocalDateTime createdAt
    ){

        this.id = id;
        this.title = title;
        this.type = type;
        this.message = message;
        this.createdAt = createdAt;

    }



    public Long getId(){
        return id;
    }


    public String getTitle(){
        return title;
    }


    public String getType(){
        return type;
    }


    public String getMessage(){
        return message;
    }


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}