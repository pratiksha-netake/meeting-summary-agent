package com.meetingsummary.meeting_summary_agent.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.ActionItemRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dto.DashboardStatisticsResponse;

@Service
public class DashboardStatisticsService {

    private final MeetingRepository meetingRepository;

    private final MeetingSummaryRepository meetingSummaryRepository;

    private final ActionItemRepository actionItemRepository;


    public DashboardStatisticsService(
            MeetingRepository meetingRepository,
            MeetingSummaryRepository meetingSummaryRepository,
            ActionItemRepository actionItemRepository) {

        this.meetingRepository = meetingRepository;
        this.meetingSummaryRepository = meetingSummaryRepository;
        this.actionItemRepository = actionItemRepository;
    }


    public DashboardStatisticsResponse getStatistics() {

        long totalMeetings =
                meetingRepository.count();

        long totalSummaries =
                meetingSummaryRepository.count();

        long totalActions =
                actionItemRepository.count();


        long pending =
                actionItemRepository.countByStatus("PENDING");

        long completed =
                actionItemRepository.countByStatus("COMPLETED");

        LocalDateTime sevenDaysAgo =
                LocalDateTime.now().minusDays(7);
        long recent =
                meetingRepository
                        .countByCreatedAtAfter(sevenDaysAgo);

        return new DashboardStatisticsResponse(

                totalMeetings,
                totalSummaries,
                totalActions,
                pending,
                completed,
                recent
        );
    }
}