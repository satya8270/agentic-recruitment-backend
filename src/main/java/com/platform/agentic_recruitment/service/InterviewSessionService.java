package com.platform.agentic_recruitment.service;

import com.platform.agentic_recruitment.dto.ChatRequestDto;
import com.platform.agentic_recruitment.dto.ChatResponseDto;
import com.platform.agentic_recruitment.model.InterviewSession;
import com.platform.agentic_recruitment.repository.InterviewSessionRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewSessionService {

    @Autowired
    private ChatClient.Builder chatClientBuilder;

    @Autowired
    private InterviewSessionRepository sessionRepository;

    public InterviewSession startSession(String email, String resumeSummary) {
        InterviewSession session = new InterviewSession();
        session.setCandidateEmail(email);
        session.setResumeSummary(resumeSummary);
        session.setCreatedAt(LocalDateTime.now());
        session.setChatTranscript("Session started for: " + email);
        return sessionRepository.save(session);
    }

    public ChatResponseDto processCandidateAnswer(ChatRequestDto request) {
        InterviewSession session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        ChatClient chatClient = chatClientBuilder.build();

        String prompt = String.format("""
            You are an expert technical interviewer conducting an interactive interview.
            
            Resume Context: %s
            Chat Transcript So Far: %s
            Candidate's Latest Answer: %s
            
            Your response MUST include two parts:
            1. **Answer Evaluation**: Provide 1-2 sentences of direct, constructive feedback on the candidate's latest answer (state what was correct, missing, or needs improvement).
            2. **Next Question**: Ask a relevant technical follow-up question based on their answer and resume context.
            
            Format your output like this:
            Feedback: <Your direct feedback on their answer>
            
            Next Question: <Your follow-up question>
            """, 
            session.getResumeSummary(), 
            session.getChatTranscript() != null ? session.getChatTranscript() : "Starting interview", 
            request.getCandidateAnswer()
        );

        String aiResponse = chatClient.prompt().user(prompt).call().content();

        // Update transcript in database
        String updatedTranscript = (session.getChatTranscript() != null ? session.getChatTranscript() : "") 
                + "\nCandidate: " + request.getCandidateAnswer() 
                + "\nAI: " + aiResponse;
        session.setChatTranscript(updatedTranscript);
        sessionRepository.save(session);

        // Send the complete response containing both feedback and the next question
        return new ChatResponseDto(aiResponse, "Feedback included in response", false);
    }

    public InterviewSession appendTranscript(Long sessionId, String sender, String message) {
        InterviewSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        String currentTranscript = session.getChatTranscript() != null ? session.getChatTranscript() : "";
        session.setChatTranscript(currentTranscript + "\n" + sender + ": " + message);
        return sessionRepository.save(session);
    }

    public InterviewSession completeSession(Long sessionId, Double score) {
        InterviewSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        session.setFinalScore(score);
        return sessionRepository.save(session);
    }

    public List<InterviewSession> getCandidateHistory(String email) {
        return sessionRepository.findByCandidateEmail(email);
    }
}