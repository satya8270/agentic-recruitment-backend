package com.platform.agentic_recruitment.service;

import com.platform.agentic_recruitment.dto.AtsAnalysisDto;
import com.platform.agentic_recruitment.dto.ResumeProfileDto;
import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeParserService {

    private final ChatClient chatClient;
    private final Tika tika;

    public ResumeParserService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.tika = new Tika();
    }

    /**
     * Extracts raw text from uploaded PDF, DOCX, DOC, or TXT files using Apache Tika.
     */
    public String extractTextFromFile(MultipartFile file) throws Exception {
        return this.tika.parseToString(file.getInputStream());
    }

    /**
     * Parses unstructured resume text into structured candidate JSON entity using Spring AI.
     */
    
    public ResumeProfileDto parseResumeText(String resumeText) {
        return this.chatClient.prompt()
                .system("You are an expert technical recruiter. Extract candidate details precisely and structure them cleanly.")
                .user(u -> u.text("Analyze the following resume text and map it to the requested data model:\n{resumeText}")
                          .param("resumeText", resumeText))
                .call()
                .entity(ResumeProfileDto.class);
    }
    public AtsAnalysisDto analyzeResumeAts(String resumeText) {
        return this.chatClient.prompt()
                .system("You are an enterprise ATS (Applicant Tracking System) optimization engine evaluating resumes for software engineering roles.")
                .user(u -> u.text("Evaluate the following resume. Calculate an ATS Score (0-100), key strengths, missing critical tech keywords/skills, actionable improvement points, and a summary:\n{resumeText}")
                          .param("resumeText", resumeText))
                .call()
                .entity(AtsAnalysisDto.class);
    }
}