package com.meetingsummary.meeting_summary_agent.dto;

import java.time.LocalDateTime;

public class TranscriptResponse {

    private Long id;

    private String fileName;

    private String message;

    private LocalDateTime uploadedAt;



    public TranscriptResponse(
            Long id,
            String fileName,
            String message,
            LocalDateTime uploadedAt){

        this.id=id;
        this.fileName=fileName;
        this.message=message;
        this.uploadedAt=uploadedAt;

    }



    public Long getId(){
        return id;
    }


    public String getFileName(){
        return fileName;
    }


    public String getMessage(){
        return message;
    }


    public LocalDateTime getUploadedAt(){
        return uploadedAt;
    }

}



