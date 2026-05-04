package com.flashcard.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AIService {

    private static final String API_KEY = "AIzaSyBxgd1IQLlpEHjzde1iHgJuTJsG6kwo4BU";
    private static final String API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    public String askAI(String prompt) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // body JSON đúng format của Gemini
            JsonObject body = new JsonObject();

            JsonArray contents = new JsonArray();
            JsonObject content = new JsonObject();

            JsonArray parts = new JsonArray();
            JsonObject part = new JsonObject();
            part.addProperty("text", prompt);

            parts.add(part);
            content.add("parts", parts);
            contents.add(content);

            body.add("contents", contents);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("DEBUG RESPONSE: " + response.body());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            // check lỗi trước
            if (json.has("error")) {
                return "❌ Lỗi API: " +
                        json.getAsJsonObject("error").get("message").getAsString();
            }

            // parse đúng response
            return json.getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Lỗi hệ thống!";
        }
    }
}