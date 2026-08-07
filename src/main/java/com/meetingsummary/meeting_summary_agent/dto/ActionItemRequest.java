package com.meetingsummary.meeting_summary_agent.dto;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class ActionItemRequest {


    @NotNull(message="Meeting id required")
    private Long meetingId;


    @NotBlank(message="Task required")
    private String task;


    @NotBlank(message="Member name required")
    private String assignedTo;



    public Long getMeetingId(){
        return meetingId;
    }


    public void setMeetingId(Long meetingId){
        this.meetingId=meetingId;
    }



    public String getTask(){
        return task;
    }


    public void setTask(String task){
        this.task=task;
    }



    public String getAssignedTo(){
        return assignedTo;
    }


    public void setAssignedTo(String assignedTo){
        this.assignedTo=assignedTo;
    }

}

