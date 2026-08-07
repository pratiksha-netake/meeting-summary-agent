package com.meetingsummary.meeting_summary_agent.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name="action_items")
public class ActionItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name="meeting_id")
    private MeetingNotes meeting;



    @Column(nullable=false)
    private String task;



    @Column(nullable=false)
    private String assignedTo;



    private String status;



    private LocalDateTime createdAt;





    public ActionItem(){

    }





    public ActionItem(
            MeetingNotes meeting,
            String task,
            String assignedTo
    ){

        this.meeting = meeting;

        this.task = task;

        this.assignedTo = assignedTo;

        this.status = "PENDING";

        this.createdAt = LocalDateTime.now();

    }






    public Long getId(){

        return id;

    }




    public MeetingNotes getMeeting(){

        return meeting;

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



    public void setStatus(String status){

        this.status = status;

    }



    public LocalDateTime getCreatedAt(){

        return createdAt;

    }


}