package com.platform.agentic_recruitment.dto;

public class StartSessionDto {
    private String email;
    private String resumeSummary;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getResumeSummary() { return resumeSummary; }
    public void setResumeSummary(String resumeSummary) { this.resumeSummary = resumeSummary; }
}