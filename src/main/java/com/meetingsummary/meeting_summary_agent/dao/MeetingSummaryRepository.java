package com.meetingsummary.meeting_summary_agent.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;


@Repository
public interface MeetingSummaryRepository
        extends JpaRepository<MeetingSummary,Long>{
	
	Optional<MeetingSummary> findByMeeting_Id(Long meetingId);


}