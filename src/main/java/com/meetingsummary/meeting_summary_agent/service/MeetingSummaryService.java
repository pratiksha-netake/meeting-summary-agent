package com.meetingsummary.meeting_summary_agent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.MeetingRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dto.MeetingHistoryResponse;
import com.meetingsummary.meeting_summary_agent.dto.SummaryResponse;
import com.meetingsummary.meeting_summary_agent.model.Meeting;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;

@Service
public class MeetingSummaryService {

    private final MeetingSummaryRepository meetingSummaryRepository;

    private final MeetingRepository meetingRepository;

    private final MeetingAnalysisService analysisService;

    public MeetingSummaryService(
            MeetingSummaryRepository meetingSummaryRepository,
            MeetingRepository meetingRepository,
            MeetingAnalysisService analysisService) {

        this.meetingSummaryRepository = meetingSummaryRepository;
        this.meetingRepository = meetingRepository;
        this.analysisService = analysisService;
    }

   

    public SummaryResponse generateSummary(Long meetingId) {
        Meeting meeting =
                meetingRepository.findById(meetingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Meeting not found with id: "
                                                + meetingId));
        String notes = meeting.getContent();
        if (notes == null || notes.trim().isEmpty()) {
            throw new RuntimeException(
                    "Meeting notes are empty. Cannot generate summary."
            );
        }

        String summary =
                analysisService.generateSummary(notes);
        String discussion =
                analysisService.extractDiscussionPoints(notes);
        String decisions =
                analysisService.extractDecisions(notes);
        String actions =
                analysisService.extractActionItems(notes);
        MeetingSummary meetingSummary =
                meetingSummaryRepository
                        .findByMeeting_Id(meetingId)
                        .orElse(new MeetingSummary());
        meetingSummary.setMeeting(meeting);
        meetingSummary.setSummary(summary);
        meetingSummary.setDiscussionPoints(
                discussion
        );

        meetingSummary.setDecisions(
                decisions
        );
        meetingSummary.setActionItems(
                actions
        );

        MeetingSummary saved =
                meetingSummaryRepository.save(
                        meetingSummary
                );

        return new SummaryResponse(
                saved.getId(),
                saved.getSummary(),
                saved.getDiscussionPoints(),
                saved.getDecisions(),
                saved.getActionItems(),
                saved.getCreatedAt()
        );
    }

    

    public List<MeetingHistoryResponse> getSummaryHistory() {
        List<MeetingSummary> summaries =
                meetingSummaryRepository
                        .findAllByOrderByCreatedAtDesc();
        return summaries.stream()
                .map(summary -> {
                    Meeting meeting =
                            summary.getMeeting();
                    return new MeetingHistoryResponse(
                            meeting.getId(),
                            meeting.getTitle(),
                            "SUMMARY",
                            "Summary generated",
                            summary.getCreatedAt()
                    );

                })
                .toList();
    }
}