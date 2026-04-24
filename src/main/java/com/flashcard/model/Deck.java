package com.flashcard.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private int id;
    private String name;
    private String description;
    private List<Card> cards = new ArrayList<>();

    public Deck() {}

    public Deck(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Card> getCards() {
        return cards;
    }
}
