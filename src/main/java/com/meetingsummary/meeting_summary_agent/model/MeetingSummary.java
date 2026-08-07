package com.meetingsummary.meeting_summary_agent.model;




import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="meeting_summary")
public class MeetingSummary {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "meeting_id")
    private MeetingNotes meeting;


    @Lob
    private String summary;


    @Lob
    private String discussionPoints;


    @Lob
    private String decisions;


    @Lob
    private String actionItems;


    private LocalDateTime createdAt;



    public MeetingSummary(){

    }



    public MeetingSummary(
            MeetingNotes meeting,
            String summary,
            String discussionPoints,
            String decisions,
            String actionItems){

        this.meeting = meeting;
        this.summary = summary;
        this.discussionPoints = discussionPoints;
        this.decisions = decisions;
        this.actionItems = actionItems;
        this.createdAt = LocalDateTime.now();
    }



    public Long getId(){
        return id;
    }


    public MeetingNotes getMeeting(){
        return meeting;
    }

    public String getSummary(){
        return summary;
    }


    public String getDiscussionPoints(){
        return discussionPoints;
    }


    public String getDecisions(){
        return decisions;
    }


    public String getActionItems(){
        return actionItems;
    }


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    
    public void setMeeting(MeetingNotes meeting){
        this.meeting = meeting;
    }


    public void setSummary(String summary){
        this.summary = summary;
    }


    public void setDiscussionPoints(String discussionPoints){
        this.discussionPoints = discussionPoints;
    }


    public void setDecisions(String decisions){
        this.decisions = decisions;
    }


    public void setActionItems(String actionItems){
        this.actionItems = actionItems;
    }

}