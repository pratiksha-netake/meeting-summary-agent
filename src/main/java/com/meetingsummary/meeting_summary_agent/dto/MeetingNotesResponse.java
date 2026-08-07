package com.meetingsummary.meeting_summary_agent.dto;




import java.time.LocalDateTime;


public class MeetingNotesResponse {


    private Long id;

    private String meetingTitle;

    private String message;

    private LocalDateTime createdAt;



    public MeetingNotesResponse(
            Long id,
            String meetingTitle,
            String message,
            LocalDateTime createdAt){


        this.id=id;
        this.meetingTitle=meetingTitle;
        this.message=message;
        this.createdAt=createdAt;

    }



    public Long getId(){
        return id;
    }


    public String getMeetingTitle(){
        return meetingTitle;
    }


    public String getMessage(){
        return message;
    }


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}