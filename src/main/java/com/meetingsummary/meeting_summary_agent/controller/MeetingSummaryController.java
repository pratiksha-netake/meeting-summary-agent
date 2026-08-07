package com.meetingsummary.meeting_summary_agent.controller;




import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meetingsummary.meeting_summary_agent.dao.MeetingNotesRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dto.SummaryResponse;
import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;
import com.meetingsummary.meeting_summary_agent.service.MeetingSummaryService;
import com.meetingsummary.meeting_summary_agent.service.PdfReportService;


@RestController
@RequestMapping("/api/summaries")
@CrossOrigin("*")
public class MeetingSummaryController {


    private final MeetingSummaryService meetingSummaryService;

    private final PdfReportService pdfReportService;

    private final MeetingNotesRepository meetingNotesRepository;

    private final MeetingSummaryRepository meetingSummaryRepository;



    public MeetingSummaryController(
            MeetingSummaryService meetingSummaryService,
            PdfReportService pdfReportService,
            MeetingNotesRepository meetingNotesRepository,
            MeetingSummaryRepository meetingSummaryRepository) {


        this.meetingSummaryService = meetingSummaryService;
        this.pdfReportService = pdfReportService;
        this.meetingNotesRepository = meetingNotesRepository;
        this.meetingSummaryRepository = meetingSummaryRepository;

    }




    @GetMapping("/test")
    public String test(){

        return "Summary Controller Working";

    }



    @PostMapping("/generate/{meetingId}")
    public ResponseEntity<?> generateSummary(
            @PathVariable Long meetingId){

        try{

            SummaryResponse response =
                    meetingSummaryService.generateSummary(meetingId);


            return ResponseEntity.ok(response);


        }catch(Exception e){

            e.printStackTrace();


            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }
   



    @GetMapping("/download/{meetingId}")
    public ResponseEntity<byte[]> downloadReport(
            @PathVariable Long meetingId) throws Exception {



        MeetingNotes meeting =
                meetingNotesRepository.findById(meetingId)
                .orElseThrow(() ->
                    new RuntimeException(
                            "Meeting not found"));



        MeetingSummary summary =
                meetingSummaryRepository
                .findByMeeting_Id(meetingId)
                .orElseThrow(() ->
                    new RuntimeException(
                            "Summary not found"));



        byte[] pdf =
                pdfReportService.generateReport(
                        meeting,
                        summary
                );



        return ResponseEntity.ok()

                .header(
                    "Content-Disposition",
                    "attachment; filename=MeetingReport.pdf"
                )

                .contentType(MediaType.APPLICATION_PDF)

                .body(pdf);

    }

}