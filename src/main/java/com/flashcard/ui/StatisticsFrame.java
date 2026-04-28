package com.flashcard.ui;

import com.flashcard.model.Statistics;
import com.flashcard.service.StatisticsService;

import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    public StatisticsFrame(int userId) {
        setTitle("Statistics");
        setSize(300, 200);
        setLocationRelativeTo(null);

        StatisticsService service = new StatisticsService();
        Statistics stats = service.getStats(userId);

        JLabel total = new JLabel("Total Cards: " + stats.getTotalCards());

        setLayout(new FlowLayout());
        add(total);
    }
}