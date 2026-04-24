package com.flashcard.dao;

import com.flashcard.model.Statistics;

import java.util.Optional;

public class StatisticsDAO {
    public Optional<Statistics> findByUserId(int userId) {
        // TODO: query statistics for the user
        return Optional.empty();
    }

    public void save(Statistics statistics) {
        // TODO: implement insert/update logic
    }
}
