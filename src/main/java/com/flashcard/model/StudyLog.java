package com.flashcard.model;

import java.time.LocalDateTime;

public class StudyLog {
    private int id;
    private int userId;
    private int cardId;
    private LocalDateTime studiedAt;
    private boolean correct;

    public StudyLog() {}

    public StudyLog(int id, int userId, int cardId, LocalDateTime studiedAt, boolean correct) {
        this.id = id;
        this.userId = userId;
        this.cardId = cardId;
        this.studiedAt = studiedAt;
        this.correct = correct;
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

    public LocalDateTime getStudiedAt() {
        return studiedAt;
    }

    public boolean isCorrect() {
        return correct;
    }
}
