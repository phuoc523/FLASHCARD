package com.flashcard.dao;

import com.flashcard.model.StudyProgress;
import java.sql.*;

public class StudyProgressDAO {

    private Connection conn;

    public StudyProgressDAO(Connection conn) {
        this.conn = conn;
    }

    public StudyProgress findByUserAndFlashcard(int userID, int flashcardID) throws Exception {

        String sql = "SELECT * FROM Study_Progress WHERE userID=? AND flashcardID=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.setInt(2, flashcardID);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            StudyProgress sp = new StudyProgress();
            sp.setProgressID(rs.getInt("progressID"));
            sp.setUserID(rs.getInt("userID"));
            sp.setFlashcardID(rs.getInt("flashcardID"));
            sp.setRepetition(rs.getInt("repetition"));
            sp.setInterval(rs.getInt("interval"));
            sp.setEaseFactor(rs.getDouble("easeFactor"));
            sp.setNextReviewDate(rs.getDate("nextReviewDate").toLocalDate());
            sp.setLastReviewed(rs.getDate("lastReviewed").toLocalDate());
            return sp;
        }
        return null;
    }

    public void insert(StudyProgress sp) throws Exception {

        String sql = "INSERT INTO Study_Progress(userID, flashcardID, repetition, interval, easeFactor, nextReviewDate, lastReviewed) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, sp.getUserID());
        ps.setInt(2, sp.getFlashcardID());
        ps.setInt(3, sp.getRepetition());
        ps.setInt(4, sp.getInterval());
        ps.setDouble(5, sp.getEaseFactor());
        ps.setDate(6, Date.valueOf(sp.getNextReviewDate()));
        ps.setDate(7, Date.valueOf(sp.getLastReviewed()));

        ps.executeUpdate();
    }

    public void update(StudyProgress sp) throws Exception {

        String sql = "UPDATE Study_Progress SET repetition=?, interval=?, easeFactor=?, nextReviewDate=?, lastReviewed=? WHERE progressID=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, sp.getRepetition());
        ps.setInt(2, sp.getInterval());
        ps.setDouble(3, sp.getEaseFactor());
        ps.setDate(4, Date.valueOf(sp.getNextReviewDate()));
        ps.setDate(5, Date.valueOf(sp.getLastReviewed()));
        ps.setInt(6, sp.getProgressID());

        ps.executeUpdate();
    }
}