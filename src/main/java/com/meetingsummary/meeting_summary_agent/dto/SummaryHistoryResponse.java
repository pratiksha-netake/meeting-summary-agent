package com.meetingsummary.meeting_summary_agent.dto;

import java.time.LocalDateTime;

public class SummaryHistoryResponse {

    private Long summaryId;
    private Long meetingId;
    private String title;
    private String type;
    private String summary;
    private String discussionPoints;
    private String decisions;
    private String actionItems;
    private LocalDateTime createdAt;

    public SummaryHistoryResponse(
            Long summaryId,
            Long meetingId,
            String title,
            String type,
            String summary,
            String discussionPoints,
            String decisions,
            String actionItems,
            LocalDateTime createdAt) {

        this.summaryId = summaryId;
        this.meetingId = meetingId;
        this.title = title;
        this.type = type;
        this.summary = summary;
        this.discussionPoints = discussionPoints;
        this.decisions = decisions;
        this.actionItems = actionItems;
        this.createdAt = createdAt;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getSummary() {
        return summary;
    }

    public String getDiscussionPoints() {
        return discussionPoints;
    }

    public String getDecisions() {
        return decisions;
    }

    public String getActionItems() {
        return actionItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}