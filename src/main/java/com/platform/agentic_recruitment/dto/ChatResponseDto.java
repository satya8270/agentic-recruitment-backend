package com.platform.agentic_recruitment.dto;

public class ChatResponseDto {
    private String aiQuestion;
    private String evaluationFeedback;
    private boolean isCompleted;

    public ChatResponseDto(String aiQuestion, String evaluationFeedback, boolean isCompleted) {
        this.aiQuestion = aiQuestion;
        this.evaluationFeedback = evaluationFeedback;
        this.isCompleted = isCompleted;
    }

    public String getAiQuestion() { return aiQuestion; }
    public String getEvaluationFeedback() { return evaluationFeedback; }
    public boolean isCompleted() { return isCompleted; }
}