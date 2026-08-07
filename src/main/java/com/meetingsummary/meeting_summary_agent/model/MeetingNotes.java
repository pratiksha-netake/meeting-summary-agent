package com.meetingsummary.meeting_summary_agent.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="meeting_notes")
public class MeetingNotes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false)
    private String meetingTitle;


    @Lob
    @Column(nullable=false)
    private String notes;
    @OneToOne(mappedBy = "meeting",
            cascade = CascadeType.ALL)
    private MeetingSummary meetingSummary;


    @OneToMany(mappedBy = "meeting",
            cascade = CascadeType.ALL)
    private List<ActionItem> actionItems = new ArrayList<>();


    private LocalDateTime createdAt;



    public MeetingSummary getMeetingSummary() {
		return meetingSummary;
	}



	public void setMeetingSummary(MeetingSummary meetingSummary) {
		this.meetingSummary = meetingSummary;
	}



	public List<ActionItem> getActionItems() {
		return actionItems;
	}



	public void setActionItems(List<ActionItem> actionItems) {
		this.actionItems = actionItems;
	}



	public MeetingNotes(){

    }



    public MeetingNotes(String meetingTitle,String notes){

        this.meetingTitle=meetingTitle;
        this.notes=notes;
        this.createdAt=LocalDateTime.now();

    }



    public Long getId(){
        return id;
    }


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


    public LocalDateTime getCreatedAt(){
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

}