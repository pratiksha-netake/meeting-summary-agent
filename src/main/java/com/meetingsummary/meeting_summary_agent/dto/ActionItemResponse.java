package com.meetingsummary.meeting_summary_agent.dto;


import java.time.LocalDateTime;

public class ActionItemResponse {


    private Long id;

    private String task;

    private String assignedTo;

    private String status;

    private LocalDateTime createdAt;



    public ActionItemResponse(
            Long id,
            String task,
            String assignedTo,
            String status,
            LocalDateTime createdAt){


        this.id=id;
        this.task=task;
        this.assignedTo=assignedTo;
        this.status=status;
        this.createdAt=createdAt;

    }



    public Long getId(){
        return id;
    }


    public String getTask(){
        return task;
    }


    public String getAssignedTo(){
        return assignedTo;
    }


    public String getStatus(){
        return status;
    }


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}
