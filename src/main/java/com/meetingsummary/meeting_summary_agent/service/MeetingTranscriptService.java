package com.meetingsummary.meeting_summary_agent.service;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meetingsummary.meeting_summary_agent.dao.MeetingTranscriptRepository;
import com.meetingsummary.meeting_summary_agent.dto.TranscriptResponse;
import com.meetingsummary.meeting_summary_agent.model.MeetingTranscript;

@Service
public class MeetingTranscriptService {


    private final MeetingTranscriptRepository repository;


    public MeetingTranscriptService(
            MeetingTranscriptRepository repository){

        this.repository=repository;

    }



    public TranscriptResponse uploadTranscript(
            MultipartFile file) throws Exception{


        String fileName=file.getOriginalFilename();


        if(fileName==null){
            throw new Exception("Invalid file");
        }


        String extension =
                fileName.substring(
                        fileName.lastIndexOf(".")+1);



        if(!extension.equalsIgnoreCase("txt")
                &&
           !extension.equalsIgnoreCase("pdf")
                &&
           !extension.equalsIgnoreCase("docx")){

            throw new Exception(
                    "Only TXT DOCX PDF files allowed");

        }



        String text =
                new String(
                        file.getBytes(),
                        StandardCharsets.UTF_8);



        MeetingTranscript transcript =
                new MeetingTranscript(
                        fileName,
                        file.getContentType(),
                        text);



        MeetingTranscript saved =
                repository.save(transcript);



        return new TranscriptResponse(
                saved.getId(),
                saved.getFileName(),
                "Transcript uploaded successfully",
                saved.getUploadedAt()
        );

    }

}