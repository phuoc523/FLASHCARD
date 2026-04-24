package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AIChatFrame extends JFrame {
    public AIChatFrame() {
        setTitle("Flashcard App - AI Chat");
        setSize(720, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("AI Chat placeholder"));
    }
}
