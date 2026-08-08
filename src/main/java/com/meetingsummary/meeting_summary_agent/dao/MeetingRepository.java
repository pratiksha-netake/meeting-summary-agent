package com.meetingsummary.meeting_summary_agent.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meetingsummary.meeting_summary_agent.model.Meeting;

public interface MeetingRepository  extends JpaRepository<Meeting,Long>{

	long countByCreatedAtAfter(LocalDateTime date);

}
