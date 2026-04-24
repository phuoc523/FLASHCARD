package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExploreFrame extends JFrame {
    public ExploreFrame() {
        setTitle("Flashcard App - Explore");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Explore placeholder"));
    }
}
