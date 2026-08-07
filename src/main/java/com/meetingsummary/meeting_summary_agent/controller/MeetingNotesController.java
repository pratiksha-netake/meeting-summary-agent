package com.meetingsummary.meeting_summary_agent.controller;


import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meetingsummary.meeting_summary_agent.dao.MeetingNotesRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingTranscriptRepository;

import com.meetingsummary.meeting_summary_agent.dto.MeetingHistoryResponse;
import com.meetingsummary.meeting_summary_agent.dto.MeetingNotesRequest;
import com.meetingsummary.meeting_summary_agent.dto.MeetingNotesResponse;

import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;
import com.meetingsummary.meeting_summary_agent.model.MeetingTranscript;

import com.meetingsummary.meeting_summary_agent.service.MeetingNotesService;
import com.meetingsummary.meeting_summary_agent.service.PdfReportService;



@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class MeetingNotesController {



    private final MeetingNotesService service;

    private final PdfReportService pdfReportService;

    private final MeetingNotesRepository meetingNotesRepository;

    private final MeetingSummaryRepository meetingSummaryRepository;

    private final MeetingTranscriptRepository transcriptRepository;




    public MeetingNotesController(
            MeetingNotesService service,
            PdfReportService pdfReportService,
            MeetingNotesRepository meetingNotesRepository,
            MeetingSummaryRepository meetingSummaryRepository,
            MeetingTranscriptRepository transcriptRepository
    ){

        this.service = service;
        this.pdfReportService = pdfReportService;
        this.meetingNotesRepository = meetingNotesRepository;
        this.meetingSummaryRepository = meetingSummaryRepository;
        this.transcriptRepository = transcriptRepository;

    }



    @PostMapping("/add")
    public ResponseEntity<MeetingNotesResponse> addNotes(
            @Valid @RequestBody MeetingNotesRequest request){


        return ResponseEntity.ok(
                service.saveNotes(request)
        );

    }


    @GetMapping("/history")
    public ResponseEntity<?> getHistory(){


        return ResponseEntity.ok(
                service.getAllMeetings()
        );

    }


    @GetMapping("/combined-history")
    public ResponseEntity<?> combinedHistory(){


        List<MeetingHistoryResponse> history =
                new ArrayList<>();



        meetingNotesRepository.findAll()
        .forEach(note -> {


            history.add(

                new MeetingHistoryResponse(

                    note.getId(),

                    note.getMeetingTitle(),

                    "MANUAL NOTES",

                    note.getNotes(),

                    note.getCreatedAt()

                )

            );


        });


        transcriptRepository.findAll()
        .forEach(file -> {


            history.add(

                new MeetingHistoryResponse(

                    file.getId(),

                    file.getFileName(),

                    "TRANSCRIPT",

                    "Uploaded transcript file",

                    file.getUploadedAt()

                )

            );


        });



        return ResponseEntity.ok(history);

    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam String keyword){


        return ResponseEntity.ok(
                service.searchMeetings(keyword)
        );

    }

    @GetMapping("/download/{meetingId}")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Long meetingId) {

        try {

            byte[] pdf;

            
            if (meetingNotesRepository.existsById(meetingId)) {

                MeetingNotes meeting = meetingNotesRepository
                        .findById(meetingId)
                        .orElseThrow(() -> new RuntimeException("Meeting not found"));

                MeetingSummary summary = meetingSummaryRepository
                        .findByMeeting_Id(meetingId)
                        .orElse(null);

                pdf = pdfReportService.generateReport(meeting, summary);

            }

           
            else if (transcriptRepository.existsById(meetingId)) {

                MeetingTranscript transcript = transcriptRepository
                        .findById(meetingId)
                        .orElseThrow(() -> new RuntimeException("Transcript not found"));

                pdf = pdfReportService.generateTranscriptReport(transcript);

            }

            else {

                throw new RuntimeException("Meeting not found");

            }

            return ResponseEntity.ok()
                    .header(
                            "Content-Disposition",
                            "attachment; filename=MeetingReport.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {          
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}