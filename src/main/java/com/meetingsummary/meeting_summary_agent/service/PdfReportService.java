package com.meetingsummary.meeting_summary_agent.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.model.Meeting;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;

@Service
public class PdfReportService {

    public byte[] generateReport(
            Meeting meeting,
            MeetingSummary summary
    ) throws IOException {

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);
            PDType1Font font =
                    new PDType1Font(
                            Standard14Fonts.FontName.HELVETICA
                    );
            PDPageContentStream content =
                    new PDPageContentStream(
                            document,
                            page
                    );
            content.beginText();
            content.setFont(font, 16);
            content.newLineAtOffset(50, 750);
            writeLine(
                    content,
                    font,
                    16,
                    "MEETING REPORT"
            );

            writeLine(
                    content,
                    font,
                    12,
                    "----------------------------------------"
            );
           writeLine(
                    content,
                    font,
                    12,
                    "Title: " +
                    safe(meeting.getTitle())
            );
            writeLine(
                    content,
                    font,
                    12,
                    "Type: " +
                    safe(meeting.getType())
            );
            writeLine(
                    content,
                    font,
                    12,
                    ""
            );
            if (summary != null) {
                writeLine(
                        content,
                        font,
                        14,
                        "SUMMARY"
                );
                writeText(
                        content,
                        font,
                        summary.getSummary()
                );
                writeLine(
                        content,
                        font,
                        12,
                        ""
                );
                writeLine(
                        content,
                        font,
                        14,
                        "DISCUSSION POINTS"
                );
                writeText(
                        content,
                        font,
                        summary.getDiscussionPoints()
                );
                writeLine(
                        content,
                        font,
                        12,
                        ""
                );
                writeLine(
                        content,
                        font,
                        14,
                        "DECISIONS"
                );
                writeText(
                        content,
                        font,
                        summary.getDecisions()
                );
                writeLine(
                        content,
                        font,
                        12,
                        ""
                );
                writeLine(
                        content,
                        font,
                        14,
                        "ACTION ITEMS"
                );
                writeText(
                        content,
                        font,
                        summary.getActionItems()
                );
            }
            content.endText();
            content.close();
            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();
            document.save(output);
            return output.toByteArray();
        }
    }
    public byte[] generateMeetingReport(
            Meeting meeting,
            MeetingSummary summary
    ) throws IOException {
       return generateReport(
                meeting,
                summary
        );
    }
    private void writeText(
            PDPageContentStream content,
            PDType1Font font,
            String text
    ) throws IOException {
        if (text == null || text.isBlank()) {
            return;
        }

        String[] lines =
                text.split("\\r?\\n");
        for (String line : lines) {
            writeLine(
                    content,
                    font,
                    12,
                    cleanText(line)
            );
        }
    }
    private void writeLine(
            PDPageContentStream content,
            PDType1Font font,
            float size,
            String text
    ) throws IOException {
        content.setFont(
                font,
                size
        );
        content.showText(
                cleanText(text)
        );
        content.newLineAtOffset(
                0,
                -18
        );
    }

    private String cleanText(String text) {
        if (text == null) {
            return "";
        }
        return text
                .replace("\r", " ")
                .replace("\n", " ")
                .replace("\t", " ")
                .replace("\u0000", "")
                .replaceAll("[^\\x20-\\x7E]", " ");
    }


    private String safe(String text) {
        return text == null
                ? ""
                : cleanText(text);
    }
}