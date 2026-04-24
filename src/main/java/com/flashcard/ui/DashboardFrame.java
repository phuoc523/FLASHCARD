package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("Flashcard App - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Dashboard placeholder"));
    }
}
