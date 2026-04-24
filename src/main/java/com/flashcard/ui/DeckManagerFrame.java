package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DeckManagerFrame extends JFrame {
    public DeckManagerFrame() {
        setTitle("Flashcard App - Deck Manager");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Deck manager placeholder"));
    }
}
