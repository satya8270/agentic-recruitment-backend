package com.platform.agentic_recruitment.repository;

import com.platform.agentic_recruitment.model.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Long> {
    
    // Custom query method to find all interview sessions belonging to a specific candidate
    List<InterviewSession> findByCandidateEmail(String candidateEmail);
}