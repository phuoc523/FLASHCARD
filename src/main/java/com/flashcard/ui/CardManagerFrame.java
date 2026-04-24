package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CardManagerFrame extends JFrame {
    public CardManagerFrame() {
        setTitle("Flashcard App - Card Manager");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Card manager placeholder"));
    }
}
