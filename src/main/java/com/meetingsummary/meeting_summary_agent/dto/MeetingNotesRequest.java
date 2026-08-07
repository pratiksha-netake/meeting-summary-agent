package com.meetingsummary.meeting_summary_agent.dto;





import jakarta.validation.constraints.NotBlank;


public class MeetingNotesRequest {


    @NotBlank(message="Meeting title required")
    private String meetingTitle;


    @NotBlank(message="Notes cannot be empty")
    private String notes;



    public String getMeetingTitle(){
        return meetingTitle;
    }


    public void setMeetingTitle(String meetingTitle){
        this.meetingTitle=meetingTitle;
    }



    public String getNotes(){
        return notes;
    }


    public void setNotes(String notes){
        this.notes=notes;
    }

}