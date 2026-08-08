package com.meetingsummary.meeting_summary_agent.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meetingsummary.meeting_summary_agent.dao.MeetingRepository;
import com.meetingsummary.meeting_summary_agent.dto.MeetingRequest;
import com.meetingsummary.meeting_summary_agent.dto.MeetingResponse;
import com.meetingsummary.meeting_summary_agent.exception.InvalidFileException;
import com.meetingsummary.meeting_summary_agent.exception.ResourceNotFoundException;
import com.meetingsummary.meeting_summary_agent.model.Meeting;

@Service
public class MeetingService {

    private final MeetingRepository repository;


    public MeetingService(MeetingRepository repository) {

        this.repository = repository;
    }

    public MeetingResponse saveMeeting(
            MeetingRequest request) {

        Meeting meeting = new Meeting(
                request.getTitle(),
                request.getContent(),
                "MANUAL"
        );

        Meeting saved = repository.save(meeting);

        return new MeetingResponse(
                saved.getId(),
                "Meeting saved successfully",
                saved.getCreatedAt()
        );
    }

    public Meeting getMeeting(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Meeting not found with id: " + id
                        )
                );
    }

    public List<Meeting> getAllMeetings() {

        return repository.findAll();
    }


    public MeetingResponse saveManualMeeting(
            MeetingRequest request) {

        return saveMeeting(request);
    }

    public Meeting uploadTranscript(
            MultipartFile file) throws IOException {

        
        if (file == null) {

            throw new InvalidFileException(
                    "Please select a file"
            );
        }


       
        if (file.isEmpty()) {
            throw new InvalidFileException(
                    "Selected file is empty"
            );
        }


      
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                fileName.trim().isEmpty()) {

            throw new InvalidFileException(
                    "Invalid file name"
            );
        }


        String extension = "";
        int dotIndex =
                fileName.lastIndexOf(".");
        if (dotIndex > 0) {

            extension =
                    fileName.substring(dotIndex + 1)
                            .toLowerCase();
        }


        if (!extension.equals("txt")
                && !extension.equals("pdf")
                && !extension.equals("doc")
                && !extension.equals("docx")) {

            throw new InvalidFileException(
                    "Only TXT, PDF, DOC and DOCX files are allowed"
            );
        }


       
        String content;
        if (extension.equals("txt")) {

            content = new String(
                    file.getBytes(),
                    StandardCharsets.UTF_8
            );

        } else {
            content =
                    "Transcript uploaded from file: "
                    + fileName;
        }


       
        Meeting meeting = new Meeting();
        meeting.setTitle(fileName);
       meeting.setContent(content);
        meeting.setType("TRANSCRIPT");
        meeting.setCreatedAt(
                LocalDateTime.now()
        );
        
        return repository.save(meeting);
    }


  
    public byte[] generatePdf(Long id) {
        return new byte[0];
    }


    public Optional<Meeting> findById(Long id) {

        return repository.findById(id);
    }
}