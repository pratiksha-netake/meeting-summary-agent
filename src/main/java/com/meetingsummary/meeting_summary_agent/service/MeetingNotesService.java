package com.meetingsummary.meeting_summary_agent.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.MeetingNotesRepository;
import com.meetingsummary.meeting_summary_agent.dto.MeetingHistory;
import com.meetingsummary.meeting_summary_agent.dto.MeetingNotesRequest;
import com.meetingsummary.meeting_summary_agent.dto.MeetingNotesResponse;
import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;



@Service
public class MeetingNotesService {



    private final MeetingNotesRepository repository;



    public MeetingNotesService(
            MeetingNotesRepository repository){

        this.repository=repository;

    }





    public MeetingNotesResponse saveNotes(
            MeetingNotesRequest request){



        MeetingNotes notes =
                new MeetingNotes(
                        request.getMeetingTitle(),
                        request.getNotes()
                );



        MeetingNotes saved =
                repository.save(notes);



        return new MeetingNotesResponse(

                saved.getId(),

                saved.getMeetingTitle(),

                "Meeting notes saved successfully",

                saved.getCreatedAt()

        );


    }
    
    
    public List<MeetingHistory> getAllMeetings(){


        return repository.findAll()
                .stream()
                .map(note ->
                new MeetingHistory(
                    note.getId(),
                    "MANUAL",
                    note.getMeetingTitle(),
                    note.getNotes(),
                    note.getCreatedAt()
                )
            )
                .toList();

    }



    public List<MeetingHistory> searchMeetings(
            String keyword){



        return repository
                .findByMeetingTitleContainingIgnoreCase(keyword)
                .stream()
                .map(note ->
                new MeetingHistory(
                    note.getId(),
                    "MANUAL",
                    note.getMeetingTitle(),
                    note.getNotes(),
                    note.getCreatedAt()
                )
            )
                .toList();


    }


}