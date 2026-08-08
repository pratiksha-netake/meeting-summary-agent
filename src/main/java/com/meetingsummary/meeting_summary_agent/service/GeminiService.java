package com.meetingsummary.meeting_summary_agent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    private final RestTemplate restTemplate;

    public GeminiService() {
        this.restTemplate = new RestTemplate();
    }

    public String generateContent(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            throw new RuntimeException("Prompt cannot be empty.");
        }

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/"
                        + model
                        + ":generateContent";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey);

        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(part));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(content));

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        try {

            ResponseEntity<Map> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            request,
                            Map.class
                    );

            if (!response.getStatusCode().is2xxSuccessful()) {

                throw new RuntimeException(
                        "Gemini API returned status: "
                                + response.getStatusCode()
                );
            }

            Map responseBody = response.getBody();

            if (responseBody == null) {
                throw new RuntimeException(
                        "Empty response received from Gemini API."
                );
            }

            return extractText(responseBody);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to generate content using Gemini: "
                            + e.getMessage(),
                    e
            );
        }
    }

    private String extractText(Map responseBody) {

        Object candidatesObject =
                responseBody.get("candidates");

        if (!(candidatesObject instanceof List)) {

            throw new RuntimeException(
                    "No candidates returned by Gemini."
            );
        }

        List candidates =
                (List) candidatesObject;

        if (candidates.isEmpty()) {

            throw new RuntimeException(
                    "Gemini returned no candidates."
            );
        }

        Object firstCandidate =
                candidates.get(0);

        if (!(firstCandidate instanceof Map)) {

            throw new RuntimeException(
                    "Invalid Gemini candidate response."
            );
        }

        Map candidate =
                (Map) firstCandidate;

        Object contentObject =
                candidate.get("content");

        if (!(contentObject instanceof Map)) {

            throw new RuntimeException(
                    "Gemini response does not contain content."
            );
        }

        Map content =
                (Map) contentObject;

        Object partsObject =
                content.get("parts");

        if (!(partsObject instanceof List)) {

            throw new RuntimeException(
                    "Gemini response does not contain parts."
            );
        }

        List parts =
                (List) partsObject;

        if (parts.isEmpty()) {

            throw new RuntimeException(
                    "Gemini response contains no text."
            );
        }

        Object firstPart =
                parts.get(0);

        if (!(firstPart instanceof Map)) {

            throw new RuntimeException(
                    "Invalid Gemini part response."
            );
        }

        Map part =
                (Map) firstPart;

        Object text =
                part.get("text");

        if (text == null) {

            throw new RuntimeException(
                    "Gemini response contains no text."
            );
        }

        return text.toString().trim();
    }
}