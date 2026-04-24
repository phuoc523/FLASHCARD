package com.flashcard.dao;

import com.flashcard.model.StudyProgress;

import java.util.Optional;

public class StudyProgressDAO {
    public Optional<StudyProgress> findByCardId(int cardId) {
        // TODO: query study progress for a card
        return Optional.empty();
    }

    public void save(StudyProgress progress) {
        // TODO: implement insert/update logic
    }
}
