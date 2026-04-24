package com.flashcard.service;

import com.flashcard.algorithm.SM2Algorithm;
import com.flashcard.dao.StudyLogDAO;
import com.flashcard.dao.StudyProgressDAO;
import com.flashcard.model.Card;
import com.flashcard.model.StudyLog;
import com.flashcard.model.StudyProgress;

public class StudyService {
    private final StudyProgressDAO progressDAO = new StudyProgressDAO();
    private final StudyLogDAO logDAO = new StudyLogDAO();
    private final SM2Algorithm sm2 = new SM2Algorithm();

    public void reviewCard(Card card, boolean correct) {
        // TODO: implement review flow using SM2
        StudyLog log = new StudyLog();
        logDAO.save(log);
    }

    public StudyProgress getProgressForCard(int cardId) {
        return progressDAO.findByCardId(cardId).orElse(new StudyProgress());
    }
}
