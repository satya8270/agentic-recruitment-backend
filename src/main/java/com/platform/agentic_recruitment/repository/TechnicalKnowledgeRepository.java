package com.platform.agentic_recruitment.repository;

import com.platform.agentic_recruitment.model.TechnicalKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalKnowledgeRepository extends JpaRepository<TechnicalKnowledge, Long> {
    List<TechnicalKnowledge> findByCategoryIgnoreCase(String category);
}