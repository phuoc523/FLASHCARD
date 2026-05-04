package com.flashcard.service;

import com.flashcard.dao.StudyProgressDAO;
import com.flashcard.dao.StudyLogDAO;
import com.flashcard.model.StudyProgress;
import com.flashcard.model.StudyLog;
import com.flashcard.algorithm.SM2Algorithm;

import java.sql.Connection;

public class StudyService {

    private StudyProgressDAO progressDAO;
    private StudyLogDAO logDAO;
    private Connection conn;

    public StudyService(Connection conn) {
        this.conn = conn;
        this.progressDAO = new StudyProgressDAO(conn);
        this.logDAO = new StudyLogDAO(conn);
    }

    public void reviewFlashcard(int userID, int flashcardID, int quality) throws Exception {

        // ✅ Validate input
        if (userID <= 0 || flashcardID <= 0) {
            throw new IllegalArgumentException("Invalid userID or flashcardID");
        }

        if (quality < 0 || quality > 5) {
            throw new IllegalArgumentException("Quality must be between 0 and 5");
        }

        try {
            conn.setAutoCommit(false); // ✅ transaction

            StudyProgress progress = progressDAO.findByUserAndFlashcard(userID, flashcardID);

            if (progress == null) {
                progress = new StudyProgress();
                progress.setUserID(userID);
                progress.setFlashcardID(flashcardID);
                SM2Algorithm.update(progress, quality);
                progressDAO.insert(progress);
            } else {
                SM2Algorithm.update(progress, quality);
                progressDAO.update(progress);
            }

            StudyLog log = new StudyLog(
                    userID,
                    flashcardID,
                    quality,
                    progress.getInterval(),
                    progress.getEaseFactor()
            );

            logDAO.insert(log);

            conn.commit(); // ✅ commit

        } catch (Exception e) {
            conn.rollback(); // ❗ rollback nếu lỗi
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}