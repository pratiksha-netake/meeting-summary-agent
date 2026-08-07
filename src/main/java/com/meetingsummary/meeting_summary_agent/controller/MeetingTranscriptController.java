package com.meetingsummary.meeting_summary_agent.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.meetingsummary.meeting_summary_agent.dto.TranscriptResponse;
import com.meetingsummary.meeting_summary_agent.service.MeetingTranscriptService;



@RestController
@RequestMapping("/api/transcripts")
@CrossOrigin("*")
public class MeetingTranscriptController {



    private final MeetingTranscriptService service;



    public MeetingTranscriptController(
            MeetingTranscriptService service){

        this.service=service;

    }



    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file){


        try{


            TranscriptResponse response =
                    service.uploadTranscript(file);


            return ResponseEntity.ok(response);


        }
        catch(Exception e){


            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }


    }


}