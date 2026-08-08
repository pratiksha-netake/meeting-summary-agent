package com.meetingsummary.meeting_summary_agent.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meetingsummary.meeting_summary_agent.dao.MeetingRepository;
import com.meetingsummary.meeting_summary_agent.dao.MeetingSummaryRepository;
import com.meetingsummary.meeting_summary_agent.dto.MeetingHistoryResponse;
import com.meetingsummary.meeting_summary_agent.dto.SummaryResponse;
import com.meetingsummary.meeting_summary_agent.model.Meeting;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;
import com.meetingsummary.meeting_summary_agent.service.MeetingSummaryService;
import com.meetingsummary.meeting_summary_agent.service.PdfReportService;

@RestController
@RequestMapping("/api/summaries")
@CrossOrigin("*")
public class MeetingSummaryController {

    private final MeetingSummaryService meetingSummaryService;

    private final PdfReportService pdfReportService;

    private final MeetingRepository meetingRepository;

    private final MeetingSummaryRepository meetingSummaryRepository;

    public MeetingSummaryController(
            MeetingSummaryService meetingSummaryService,
            PdfReportService pdfReportService,
            MeetingRepository meetingRepository,
            MeetingSummaryRepository meetingSummaryRepository) {

        this.meetingSummaryService =
                meetingSummaryService;

        this.pdfReportService =
                pdfReportService;

        this.meetingRepository =
                meetingRepository;

        this.meetingSummaryRepository =
                meetingSummaryRepository;
    }


    @GetMapping("/test")
    public String test() {

        return "Summary Controller Working";
    }


    @PostMapping("/generate/{meetingId}")
    public ResponseEntity<?> generateSummary(
            @PathVariable Long meetingId) {

        try {

            SummaryResponse response =
                    meetingSummaryService
                            .generateSummary(meetingId);

            return ResponseEntity.ok(response);

        }
        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    @GetMapping("/history")
    public ResponseEntity<List<MeetingHistoryResponse>>
    getSummaryHistory() {

        return ResponseEntity.ok(
                meetingSummaryService
                        .getSummaryHistory()
        );
    }


    @GetMapping("/download/{meetingId}")
    public ResponseEntity<byte[]> downloadReport(
            @PathVariable Long meetingId)
            throws Exception {

        Meeting meeting =
                meetingRepository
                        .findById(meetingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Meeting not found"
                                ));

        MeetingSummary summary =
                meetingSummaryRepository
                        .findByMeeting_Id(meetingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Summary not found. Generate summary first."
                                ));

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
                .contentType(
                        MediaType.APPLICATION_PDF
                )
                .body(pdf);
    }
}