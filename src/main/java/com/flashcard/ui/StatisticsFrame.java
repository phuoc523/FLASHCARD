package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StatisticsFrame extends JFrame {
    public StatisticsFrame() {
        setTitle("Flashcard App - Statistics");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Statistics placeholder"));
    }
}
