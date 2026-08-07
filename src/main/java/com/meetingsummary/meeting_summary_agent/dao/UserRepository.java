package com.meetingsummary.meeting_summary_agent.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingsummary.meeting_summary_agent.model.User;


@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

	 boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

}
