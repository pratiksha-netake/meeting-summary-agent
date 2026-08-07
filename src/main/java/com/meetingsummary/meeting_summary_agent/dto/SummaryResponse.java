package com.meetingsummary.meeting_summary_agent.dto;



import java.time.LocalDateTime;



public class SummaryResponse {


    private Long id;

    private String summary;

    private String discussionPoints;

    private String decisions;

    private String actionItems;

    private LocalDateTime createdAt;



    public SummaryResponse(
            Long id,
            String summary,
            String discussionPoints,
            String decisions,
            String actionItems,
            LocalDateTime createdAt){


        this.id=id;
        this.summary=summary;
        this.discussionPoints=discussionPoints;
        this.decisions=decisions;
        this.actionItems=actionItems;
        this.createdAt=createdAt;

    }



    public Long getId(){
        return id;
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

}