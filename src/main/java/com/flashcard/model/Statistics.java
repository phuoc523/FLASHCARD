package com.flashcard.model;

public class Statistics {
    private int totalCards;
    private int learnedCards;
    private int reviewsToday;

    public int getTotalCards() { return totalCards; }
    public void setTotalCards(int totalCards) { this.totalCards = totalCards; }

    public int getLearnedCards() { return learnedCards; }
    public void setLearnedCards(int learnedCards) { this.learnedCards = learnedCards; }

    public int getReviewsToday() { return reviewsToday; }
    public void setReviewsToday(int reviewsToday) { this.reviewsToday = reviewsToday; }
}