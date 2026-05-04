package com.flashcard.model;

import java.time.LocalDate;

public class Deck {
    private int id;
    private String name;
    private String description;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    private int userId;
    public int getUserId()           { return userId; }
    public void setUserId(int userId){ this.userId = userId; }
}