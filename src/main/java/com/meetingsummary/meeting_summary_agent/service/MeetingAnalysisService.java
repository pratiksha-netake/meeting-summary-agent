package com.meetingsummary.meeting_summary_agent.service;


import org.springframework.stereotype.Service;

@Service
public class MeetingAnalysisService {

    public String generateSummary(String notes) {

        if (notes == null || notes.isBlank()) {
            return "No meeting notes available.";
        }

        if (notes.length() <= 250) {
            return notes;
        }

        return notes.substring(0, 250) + "...";
    }

    public String extractDiscussionPoints(String notes) {

        StringBuilder builder = new StringBuilder();

        String[] lines = notes.split("\\.");

        int count = 1;

        for (String line : lines) {

            if (!line.trim().isEmpty()) {

                builder.append(count++)
                        .append(". ")
                        .append(line.trim())
                        .append("\n");
            }

            if (count > 5)
                break;
        }

        return builder.toString();
    }

    public String extractDecisions(String notes) {

        StringBuilder builder = new StringBuilder();

        String[] lines = notes.split("\\.");

        int count = 1;

        for (String line : lines) {

            String lower = line.toLowerCase();

            if (lower.contains("decided")
                    || lower.contains("approved")
                    || lower.contains("agreed")
                    || lower.contains("finalized")) {

                builder.append(count++)
                        .append(". ")
                        .append(line.trim())
                        .append("\n");
            }

        }

        if (builder.length() == 0) {
            return "No decisions found.";
        }

        return builder.toString();
    }

    public String extractActionItems(String notes) {

        StringBuilder builder = new StringBuilder();

        String[] lines = notes.split("\\.");

        int count = 1;

        for (String line : lines) {

            String lower = line.toLowerCase();

            if (lower.contains("will")
                    || lower.contains("must")
                    || lower.contains("complete")
                    || lower.contains("finish")
                    || lower.contains("assign")) {

                builder.append(count++)
                        .append(". ")
                        .append(line.trim())
                        .append("\n");
            }

        }

        if (builder.length() == 0) {
            return "No action items found.";
        }

        return builder.toString();
    }

}