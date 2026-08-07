package com.meetingsummary.meeting_summary_agent.service;


import org.springframework.stereotype.Service;


import com.meetingsummary.meeting_summary_agent.dao.MeetingNotesRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dto.SummaryResponse;
import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;



@Service
public class MeetingSummaryService {


    private final MeetingSummaryRepository meetingSummaryRepository;

    private final MeetingNotesRepository meetingNotesRepository;

    private final MeetingAnalysisService analysisService;



    public MeetingSummaryService(
            MeetingSummaryRepository meetingSummaryRepository,
            MeetingNotesRepository meetingNotesRepository,
            MeetingAnalysisService analysisService
    ){

        this.meetingSummaryRepository = meetingSummaryRepository;
        this.meetingNotesRepository = meetingNotesRepository;
        this.analysisService = analysisService;

    }





    public SummaryResponse generateSummary(Long meetingId){



        MeetingNotes meeting =
                meetingNotesRepository.findById(meetingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Meeting not found with id : "
                                + meetingId
                        )
                );



        String notes = meeting.getNotes();



        if(notes == null || notes.trim().isEmpty()){

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
                .orElse(
                    new MeetingSummary()
                );



        meetingSummary.setMeeting(meeting);

        meetingSummary.setSummary(summary);

        meetingSummary.setDiscussionPoints(discussion);

        meetingSummary.setDecisions(decisions);

        meetingSummary.setActionItems(actions);






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


}