package com.platform.agentic_recruitment.dto;

import java.util.List;

public class AtsAnalysisDto {
    private int atsScore;
    private String summary;
    private List<String> strengths;
    private List<String> missingKeywords;
    private List<String> improvementPoints;

    public int getAtsScore() { return atsScore; }
    public void setAtsScore(int atsScore) { this.atsScore = atsScore; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public List<String> getStrengths() { return strengths; }
    public void setStrengths(List<String> strengths) { this.strengths = strengths; }

    public List<String> getMissingKeywords() { return missingKeywords; }
    public void setMissingKeywords(List<String> missingKeywords) { this.missingKeywords = missingKeywords; }

    public List<String> getImprovementPoints() { return improvementPoints; }
    public void setImprovementPoints(List<String> improvementPoints) { this.improvementPoints = improvementPoints; }
}