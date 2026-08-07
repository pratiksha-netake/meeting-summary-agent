package com.meetingsummary.meeting_summary_agent.dto;





import jakarta.validation.constraints.NotNull;


public class SummaryRequest {


    @NotNull(message="Meeting id required")
    private Long meetingId;



    public Long getMeetingId(){

        return meetingId;

    }



    public void setMeetingId(Long meetingId){

        this.meetingId=meetingId;

    }

}