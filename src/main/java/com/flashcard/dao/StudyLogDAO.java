package com.flashcard.dao;

import com.flashcard.model.StudyLog;
import java.sql.*;

public class StudyLogDAO {

    private Connection conn;

    public StudyLogDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(StudyLog log) throws Exception {

        String sql = "INSERT INTO StudyLog(userID, flashcardID, quality, reviewTime, intervalAfter, easeFactorAfter) VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, log.getUserID());
        ps.setInt(2, log.getFlashcardID());
        ps.setInt(3, log.getQuality());
        ps.setTimestamp(4, Timestamp.valueOf(log.getReviewTime()));
        ps.setInt(5, log.getIntervalAfter());
        ps.setDouble(6, log.getEaseFactorAfter());

        ps.executeUpdate();
    }
}