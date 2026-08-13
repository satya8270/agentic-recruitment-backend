package com.platform.agentic_recruitment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "technical_knowledge")
public class TechnicalKnowledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category; // e.g., "Java", "Spring", "System Design"

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionContent;

    @Column(columnDefinition = "TEXT")
    private String idealAnswer;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getQuestionContent() { return questionContent; }
    public void setQuestionContent(String questionContent) { this.questionContent = questionContent; }

    public String getIdealAnswer() { return idealAnswer; }
    public void setIdealAnswer(String idealAnswer) { this.idealAnswer = idealAnswer; }
}