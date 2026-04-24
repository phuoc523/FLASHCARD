package com.flashcard.model;

public class Statistics {
    private int userId;
    private int totalCards;
    private int learnedCards;
    private int studySessions;

    public Statistics() {}

    public Statistics(int userId, int totalCards, int learnedCards, int studySessions) {
        this.userId = userId;
        this.totalCards = totalCards;
        this.learnedCards = learnedCards;
        this.studySessions = studySessions;
    }

    public int getUserId() {
        return userId;
    }

    public int getTotalCards() {
        return totalCards;
    }

    public int getLearnedCards() {
        return learnedCards;
    }

    public int getStudySessions() {
        return studySessions;
    }
}
