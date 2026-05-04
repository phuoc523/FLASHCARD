package com.flashcard.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.flashcard.model.Statistics;

public class StatisticsDAO {

    public Statistics getStatistics(int userId) {
        Statistics stats = new Statistics();

        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT COUNT(*) AS total FROM cards c " +
                            "JOIN decks d ON c.deck_id = d.id " +
                            "WHERE d.user_id = " + userId
            );

            if (rs.next()) {
                stats.setTotalCards(rs.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }
}