package com.flashcard.service;

import com.flashcard.dao.StatisticsDAO;
import com.flashcard.model.Statistics;

public class StatisticsService {
    private final StatisticsDAO statisticsDAO = new StatisticsDAO();

    public Statistics getStatistics(int userId) {
        return statisticsDAO.findByUserId(userId).orElse(new Statistics(userId, 0, 0, 0));
    }

    public void saveStatistics(Statistics statistics) {
        statisticsDAO.save(statistics);
    }
}
