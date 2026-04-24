package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudyFrame extends JFrame {
    public StudyFrame() {
        setTitle("Flashcard App - Study");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Study session placeholder"));
    }
}
