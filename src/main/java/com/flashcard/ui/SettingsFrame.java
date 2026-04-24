package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SettingsFrame extends JFrame {
    public SettingsFrame() {
        setTitle("Flashcard App - Settings");
        setSize(650, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Settings placeholder"));
    }
}
