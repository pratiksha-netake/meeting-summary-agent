package com.meetingsummary.meeting_summary_agent.dto;

import java.time.LocalDateTime;


public class MeetingHistory {


    private Long id;

    private String type;

    private String title;

    private String content;

    private LocalDateTime createdAt;



    public MeetingHistory(
            Long id,
            String type,
            String title,
            String content,
            LocalDateTime createdAt
    ){

        this.id=id;
        this.type=type;
        this.title=title;
        this.content=content;
        this.createdAt=createdAt;

    }



    public Long getId(){
        return id;
    }


    public String getType(){
        return type;
    }


    public String getTitle(){
        return title;
    }


    public String getContent(){
        return content;
    }


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}