package com.flashcard.model;

import java.time.LocalDateTime;

public class StudyLog {

    private int logID;
    private int userID;
    private int flashcardID;

    private int quality;
    private LocalDateTime reviewTime;

    private int intervalAfter;
    private double easeFactorAfter;

    public StudyLog() {
        this.reviewTime = LocalDateTime.now();
    }

    public StudyLog(int userID, int flashcardID, int quality,
                    int intervalAfter, double easeFactorAfter) {
        this.userID = userID;
        this.flashcardID = flashcardID;
        this.quality = quality;
        this.intervalAfter = intervalAfter;
        this.easeFactorAfter = easeFactorAfter;
        this.reviewTime = LocalDateTime.now();
    }

    // Getter & Setter

    public int getLogID() { return logID; }
    public void setLogID(int logID) { this.logID = logID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getFlashcardID() { return flashcardID; }
    public void setFlashcardID(int flashcardID) { this.flashcardID = flashcardID; }

    public int getQuality() { return quality; }
    public void setQuality(int quality) { this.quality = quality; }

    public LocalDateTime getReviewTime() { return reviewTime; }
    public void setReviewTime(LocalDateTime reviewTime) { this.reviewTime = reviewTime; }

    public int getIntervalAfter() { return intervalAfter; }
    public void setIntervalAfter(int intervalAfter) { this.intervalAfter = intervalAfter; }

    public double getEaseFactorAfter() { return easeFactorAfter; }
    public void setEaseFactorAfter(double easeFactorAfter) { this.easeFactorAfter = easeFactorAfter; }
}