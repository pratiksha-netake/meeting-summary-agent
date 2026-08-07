package com.meetingsummary.meeting_summary_agent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingsummary.meeting_summary_agent.model.MeetingTranscript;

@Repository
public interface MeetingTranscriptRepository 
        extends JpaRepository<MeetingTranscript,Long>{

}