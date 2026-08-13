package com.platform.agentic_recruitment.dto;

import java.util.List;

public record ResumeProfileDto(
    String fullName,
    String email,
    List<String> coreSkills,
    List<String> keyProjects,
    int yearsOfExperience
) {}