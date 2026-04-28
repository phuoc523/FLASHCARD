package com.flashcard.service;

import com.flashcard.dao.StatisticsDAO;
import com.flashcard.model.Statistics;

public class StatisticsService {

    private StatisticsDAO dao = new StatisticsDAO();

    public Statistics getStats(int userId) {
        return dao.getStatistics(userId);
    }
}