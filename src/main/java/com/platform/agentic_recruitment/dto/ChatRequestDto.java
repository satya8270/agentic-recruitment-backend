package com.platform.agentic_recruitment.dto;

public class ChatRequestDto {
    private Long sessionId;
    private String candidateAnswer;

    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }
    public String getCandidateAnswer() { return candidateAnswer; }
    public void setCandidateAnswer(String candidateAnswer) { this.candidateAnswer = candidateAnswer; }
}