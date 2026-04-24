package com.flashcard.model;

public class StudyProgress {
    private int id;
    private int userId;
    private int cardId;
    private int reviewCount;
    private double easeFactor;

    public StudyProgress() {}

    public StudyProgress(int id, int userId, int cardId, int reviewCount, double easeFactor) {
        this.id = id;
        this.userId = userId;
        this.cardId = cardId;
        this.reviewCount = reviewCount;
        this.easeFactor = easeFactor;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCardId() {
        return cardId;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public double getEaseFactor() {
        return easeFactor;
    }
}
