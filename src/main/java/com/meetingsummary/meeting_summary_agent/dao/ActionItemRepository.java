package com.meetingsummary.meeting_summary_agent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingsummary.meeting_summary_agent.model.ActionItem;


@Repository
public interface ActionItemRepository
        extends JpaRepository<ActionItem,Long>{
	  long countByStatus(String status);

}