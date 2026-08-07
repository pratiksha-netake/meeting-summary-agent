package com.meetingsummary.meeting_summary_agent.dao;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;


@Repository
public interface MeetingNotesRepository 
        extends JpaRepository<MeetingNotes,Long>{
	
	  List<MeetingNotes> findByMeetingTitleContainingIgnoreCase(
	            String keyword
	    );
	  
	  Optional<MeetingNotes> findById(Long id);
	  
	  long countByCreatedAtAfter(
		        java.time.LocalDateTime date
		);

}