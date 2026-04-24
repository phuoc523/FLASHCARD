package com.flashcard.model;

public class Card {
    private int id;
    private int deckId;
    private String front;
    private String back;

    public Card() {}

    public Card(int id, int deckId, String front, String back) {
        this.id = id;
        this.deckId = deckId;
        this.front = front;
        this.back = back;
    }

    public int getId() {
        return id;
    }

    public int getDeckId() {
        return deckId;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }
}
