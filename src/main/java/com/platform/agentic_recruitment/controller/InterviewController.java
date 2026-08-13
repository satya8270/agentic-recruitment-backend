package com.platform.agentic_recruitment.controller;

import com.platform.agentic_recruitment.dto.ChatRequestDto;
import com.platform.agentic_recruitment.dto.ChatResponseDto;
import com.platform.agentic_recruitment.dto.StartSessionDto;
import com.platform.agentic_recruitment.model.InterviewSession;
import com.platform.agentic_recruitment.service.InterviewSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin(origins = "http://localhost:4200")
public class InterviewController {

    private final InterviewSessionService sessionService;

    public InterviewController(InterviewSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/start")
    public ResponseEntity<InterviewSession> startInterview(@RequestBody StartSessionDto request) {
        InterviewSession session = sessionService.startSession(request.getEmail(), request.getResumeSummary());
        return ResponseEntity.ok(session);
    }

    @PostMapping("/next-question")
    public ResponseEntity<ChatResponseDto> getNextQuestion(@RequestBody ChatRequestDto request) {
        ChatResponseDto response = sessionService.processCandidateAnswer(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{sessionId}/message")
    public ResponseEntity<InterviewSession> sendMessage(
            @PathVariable Long sessionId, 
            @RequestParam String sender, 
            @RequestBody String message) {
        InterviewSession updatedSession = sessionService.appendTranscript(sessionId, sender, message);
        return ResponseEntity.ok(updatedSession);
    }

    @PostMapping("/{sessionId}/complete")
    public ResponseEntity<InterviewSession> completeInterview(
            @PathVariable Long sessionId, 
            @RequestParam Double score) {
        InterviewSession completedSession = sessionService.completeSession(sessionId, score);
        return ResponseEntity.ok(completedSession);
    }

    @GetMapping("/history")
    public ResponseEntity<List<InterviewSession>> getHistory(@RequestParam String email) {
        List<InterviewSession> history = sessionService.getCandidateHistory(email);
        return ResponseEntity.ok(history);
    }
}