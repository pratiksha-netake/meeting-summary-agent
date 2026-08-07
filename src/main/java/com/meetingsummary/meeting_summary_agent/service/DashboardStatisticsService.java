package com.meetingsummary.meeting_summary_agent.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.ActionItemRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingNotesRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dto.DashboardStatisticsResponse;



@Service
public class DashboardStatisticsService {



    private final MeetingNotesRepository meetingNotesRepository;

    private final MeetingSummaryRepository meetingSummaryRepository;

    private final ActionItemRepository actionItemRepository;




    public DashboardStatisticsService(
            MeetingNotesRepository meetingNotesRepository,
            MeetingSummaryRepository meetingSummaryRepository,
            ActionItemRepository actionItemRepository
    ){

        this.meetingNotesRepository = meetingNotesRepository;
        this.meetingSummaryRepository = meetingSummaryRepository;
        this.actionItemRepository = actionItemRepository;

    }






    public DashboardStatisticsResponse getStatistics(){


        long totalMeetings =
                meetingNotesRepository.count();



        long totalSummaries =
                meetingSummaryRepository.count();



        long totalActions =
                actionItemRepository.count();



        long pending =
                actionItemRepository.countByStatus("PENDING");



        long completed =
                actionItemRepository.countByStatus("COMPLETED");




        LocalDateTime date =
                LocalDateTime.now()
                .minusDays(7);



        long recent =
                meetingNotesRepository
                .countByCreatedAtAfter(date);





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