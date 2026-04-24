package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Flashcard App - Register");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Registration screen placeholder"));
    }
}
