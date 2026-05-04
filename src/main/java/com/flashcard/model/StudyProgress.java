package com.flashcard.model;

import java.time.LocalDate;

public class StudyProgress {

    private int progressID;
    private int userID;
    private int flashcardID;

    private int repetition;
    private int interval;
    private double easeFactor;

    private LocalDate nextReviewDate;
    private LocalDate lastReviewed;

    public StudyProgress() {
        this.repetition = 0;
        this.interval = 0;
        this.easeFactor = 2.5;
        this.nextReviewDate = LocalDate.now();
        this.lastReviewed = LocalDate.now();
    }

    public int getProgressID() { return progressID; }
    public void setProgressID(int progressID) { this.progressID = progressID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getFlashcardID() { return flashcardID; }
    public void setFlashcardID(int flashcardID) { this.flashcardID = flashcardID; }

    public int getRepetition() { return repetition; }
    public void setRepetition(int repetition) { this.repetition = repetition; }

    public int getInterval() { return interval; }
    public void setInterval(int interval) { this.interval = interval; }

    public double getEaseFactor() { return easeFactor; }
    public void setEaseFactor(double easeFactor) { this.easeFactor = easeFactor; }

    public LocalDate getNextReviewDate() { return nextReviewDate; }
    public void setNextReviewDate(LocalDate nextReviewDate) { this.nextReviewDate = nextReviewDate; }

    public LocalDate getLastReviewed() { return lastReviewed; }
    public void setLastReviewed(LocalDate lastReviewed) { this.lastReviewed = lastReviewed; }
}