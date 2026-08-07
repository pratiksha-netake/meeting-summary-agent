package com.meetingsummary.meeting_summary_agent.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="meeting_transcripts")
public class MeetingTranscript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private String transcriptText;

    private LocalDateTime uploadedAt;


    public MeetingTranscript(){

    }


    public MeetingTranscript(String fileName,String fileType,String transcriptText){

        this.fileName=fileName;
        this.fileType=fileType;
        this.transcriptText=transcriptText;
        this.uploadedAt=LocalDateTime.now();

    }


    public Long getId(){
        return id;
    }


    public String getFileName(){
        return fileName;
    }


    public void setFileName(String fileName){
        this.fileName=fileName;
    }


    public String getFileType(){
        return fileType;
    }


    public void setFileType(String fileType){
        this.fileType=fileType;
    }


    public String getTranscriptText(){
        return transcriptText;
    }


    public void setTranscriptText(String transcriptText){
        this.transcriptText=transcriptText;
    }


    public LocalDateTime getUploadedAt(){
        return uploadedAt;
    }


    public void setUploadedAt(LocalDateTime uploadedAt){
        this.uploadedAt=uploadedAt;
    }

}