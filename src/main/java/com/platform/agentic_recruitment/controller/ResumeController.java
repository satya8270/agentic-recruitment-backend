package com.platform.agentic_recruitment.controller;

import com.platform.agentic_recruitment.dto.ResumeProfileDto;
import com.platform.agentic_recruitment.service.ResumeParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeParserService resumeParserService;

    public ResumeController(ResumeParserService resumeParserService) {
        this.resumeParserService = resumeParserService;
    }

    @PostMapping("/parse")
    public ResponseEntity<ResumeProfileDto> parseResume(@RequestBody String rawResumeText) {
        ResumeProfileDto profile = resumeParserService.parseResumeText(rawResumeText);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/parse-file")
    public ResponseEntity<ResumeProfileDto> parseResumeFile(@RequestParam("file") MultipartFile file) throws Exception {
        String extractedText = resumeParserService.extractTextFromFile(file);
        ResumeProfileDto profile = resumeParserService.parseResumeText(extractedText);
        return ResponseEntity.ok(profile);
    }
}