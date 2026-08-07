package com.meetingsummary.meeting_summary_agent.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.springframework.stereotype.Service;


import com.meetingsummary.meeting_summary_agent.model.MeetingNotes;
import com.meetingsummary.meeting_summary_agent.model.MeetingSummary;
import com.meetingsummary.meeting_summary_agent.model.MeetingTranscript;



@Service
public class PdfReportService {



	private PDType0Font loadFont(
	        PDDocument document
	) throws IOException {


	    InputStream fontStream =
	            PdfReportService.class
	            .getResourceAsStream(
	                    "/fonts/NotoSans-Regular.ttf"
	            );


	    if(fontStream == null){

	        throw new IOException(
	                "Missing font file: resources/fonts/NotoSans-Regular.ttf"
	        );

	    }


	    return PDType0Font.load(
	            document,
	            fontStream
	    );
	}







    public byte[] generateReport(
            MeetingNotes meeting,
            MeetingSummary summary
    ) throws IOException {



        PDDocument document =
                new PDDocument();



        PDPage page =
                new PDPage();


        document.addPage(page);



        PDType0Font font =
                loadFont(document);



        PDPageContentStream content =
                new PDPageContentStream(
                        document,
                        page
                );



        content.setFont(
                font,
                12
        );



        content.beginText();


        content.newLineAtOffset(
                50,
                750
        );



        writeLine(
                content,
                "MEETING REPORT"
        );



        writeLine(
                content,
                "---------------------"
        );



        writeLine(
                content,
                "Title : "
                + safe(meeting.getMeetingTitle())
        );



        writeLine(
                content,
                ""
        );



        if(summary != null){


            writeLine(
                    content,
                    "SUMMARY:"
            );


            writeText(
                    content,
                    summary.getSummary()
            );


            writeLine(
                    content,
                    ""
            );


            writeLine(
                    content,
                    "ACTION ITEMS:"
            );


            writeText(
                    content,
                    summary.getActionItems()
            );

        }



        content.endText();


        content.close();



        ByteArrayOutputStream output =
                new ByteArrayOutputStream();



        document.save(output);


        document.close();



        return output.toByteArray();

    }









    public byte[] generateTranscriptReport(
            MeetingTranscript transcript
    ) throws IOException {



        PDDocument document =
                new PDDocument();



        PDPage page =
                new PDPage();



        document.addPage(page);



        PDType0Font font =
                loadFont(document);




        PDPageContentStream content =
                new PDPageContentStream(
                        document,
                        page
                );



        content.setFont(
                font,
                12
        );



        content.beginText();



        content.newLineAtOffset(
                50,
                750
        );




        writeLine(
                content,
                "TRANSCRIPT REPORT"
        );



        writeLine(
                content,
                "---------------------"
        );



        writeLine(
                content,
                "File Name : "
                + safe(transcript.getFileName())
        );



        writeLine(
                content,
                ""
        );



        writeLine(
                content,
                "Transcript:"
        );



        writeText(
                content,
                transcript.getTranscriptText()
        );



        content.endText();


        content.close();



        ByteArrayOutputStream output =
                new ByteArrayOutputStream();



        document.save(output);


        document.close();



        return output.toByteArray();

    }









    private void writeText(
            PDPageContentStream content,
            String text
    ) throws IOException {


        if(text == null)
            return;



        String[] lines =
                text.split("\\r?\\n");



        for(String line : lines){

            writeLine(
                    content,
                    line
            );

        }

    }









    private void writeLine(
            PDPageContentStream content,
            String text
    ) throws IOException {



        content.showText(
                safe(text)
        );


        content.newLineAtOffset(
                0,
                -18
        );

    }








    private String safe(
            String text
    ){

        return text == null
                ? ""
                : text;

    }


}