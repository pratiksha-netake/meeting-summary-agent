package com.meetingsummary.meeting_summary_agent.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.meetingsummary.meeting_summary_agent.dto.MeetingRequest;
import com.meetingsummary.meeting_summary_agent.dto.MeetingResponse;
import com.meetingsummary.meeting_summary_agent.model.Meeting;
import com.meetingsummary.meeting_summary_agent.service.MeetingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meetings")
@CrossOrigin(origins = "*")
public class MeetingController {

    private final MeetingService service;

    public MeetingController(MeetingService service) {
        this.service = service;
    }


   
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody MeetingRequest request) {

        return ResponseEntity.ok(
                service.saveMeeting(request)
        );
    }


  
    @GetMapping("/{id}")
    public ResponseEntity<?> get(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getMeeting(id)
        );
    }



    @PostMapping("/add")
    public ResponseEntity<MeetingResponse> addMeeting(
            @Valid @RequestBody MeetingRequest request) {

        return ResponseEntity.ok(
                service.saveManualMeeting(request)
        );
    }


    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Meeting> uploadTranscript(
            @RequestParam(value = "file", required = true)
            MultipartFile file) throws IOException {

        return ResponseEntity.ok(
                service.uploadTranscript(file)
        );
    }


    @GetMapping("/history")
    public ResponseEntity<List<Meeting>> history() {

        return ResponseEntity.ok(
                service.getAllMeetings()
        );
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<Meeting> viewMeeting(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getMeeting(id)
        );
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable Long id) throws Exception {

        byte[] pdf = service.generatePdf(id);

        return ResponseEntity.ok()
                .header(
                        "Content-Disposition",
                        "attachment; filename=MeetingReport.pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}