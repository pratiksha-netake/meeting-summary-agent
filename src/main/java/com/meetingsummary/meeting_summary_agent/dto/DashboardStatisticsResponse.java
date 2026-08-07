package com.meetingsummary.meeting_summary_agent.dto;


public class DashboardStatisticsResponse {


    private long totalMeetings;

    private long totalSummaries;

    private long totalActionItems;

    private long pendingActions;

    private long completedActions;

    private long recentMeetings;



    public DashboardStatisticsResponse(
            long totalMeetings,
            long totalSummaries,
            long totalActionItems,
            long pendingActions,
            long completedActions,
            long recentMeetings
    ){

        this.totalMeetings = totalMeetings;
        this.totalSummaries = totalSummaries;
        this.totalActionItems = totalActionItems;
        this.pendingActions = pendingActions;
        this.completedActions = completedActions;
        this.recentMeetings = recentMeetings;

    }



    public long getTotalMeetings(){
        return totalMeetings;
    }


    public long getTotalSummaries(){
        return totalSummaries;
    }


    public long getTotalActionItems(){
        return totalActionItems;
    }


    public long getPendingActions(){
        return pendingActions;
    }


    public long getCompletedActions(){
        return completedActions;
    }


    public long getRecentMeetings(){
        return recentMeetings;
    }

}