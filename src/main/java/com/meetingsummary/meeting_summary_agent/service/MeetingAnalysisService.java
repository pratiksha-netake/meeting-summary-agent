package com.meetingsummary.meeting_summary_agent.service;

import org.springframework.stereotype.Service;

@Service
public class MeetingAnalysisService {

    private final GeminiService geminiService;

    public MeetingAnalysisService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String generateSummary(String notes) {

        if (notes == null || notes.isBlank()) {
            return "No meeting notes available.";
        }

        String prompt = """
                You are a professional meeting assistant.

                Summarize the following meeting notes in a concise and professional way.

                Requirements:
                - Provide only the meeting summary.
                - Focus on the main topics discussed.
                - Mention important outcomes.
                - Do not add information that is not present in the meeting notes.
                - Keep the summary clear and concise.

                Meeting Notes:
                %s
                """.formatted(notes);

        return geminiService.generateContent(prompt);
    }

    public String extractDiscussionPoints(String notes) {

        if (notes == null || notes.isBlank()) {
            return "No discussion points found.";
        }

        String prompt = """
                You are a professional meeting assistant.

                Extract the key discussion points from the following meeting notes.

                Requirements:
                - Return only the important discussion points.
                - Use a numbered list.
                - Keep each point concise.
                - Do not invent information.
                - Include only topics that were actually discussed.

                Meeting Notes:
                %s
                """.formatted(notes);

        return geminiService.generateContent(prompt);
    }

    public String extractDecisions(String notes) {

        if (notes == null || notes.isBlank()) {
            return "No decisions found.";
        }

        String prompt = """
                You are a professional meeting assistant.

                Identify the important decisions made during the following meeting.

                Requirements:
                - Return only decisions that were actually made.
                - Use a numbered list.
                - Keep each decision concise.
                - Do not confuse discussion topics with final decisions.
                - Do not invent any decisions.
                - If there are no clear decisions, return exactly:
                  No decisions found.

                Meeting Notes:
                %s
                """.formatted(notes);

        return geminiService.generateContent(prompt);
    }

    public String extractActionItems(String notes) {

        if (notes == null || notes.isBlank()) {
            return "No action items found.";
        }

        String prompt = """
                You are a professional meeting assistant.

                Extract all action items from the following meeting notes.

                Requirements:
                - Return the action items as a numbered list.
                - Include the responsible person if mentioned.
                - Include the task or responsibility.
                - Include the deadline if mentioned.
                - Do not invent names, tasks, or deadlines.
                - Include only tasks that require an action after the meeting.
                - If there are no action items, return exactly:
                  No action items found.

                Meeting Notes:
                %s
                """.formatted(notes);

        return geminiService.generateContent(prompt);
    }
}