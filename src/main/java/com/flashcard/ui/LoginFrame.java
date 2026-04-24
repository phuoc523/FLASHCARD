package com.flashcard.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Flashcard App - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Login screen placeholder"));
    }
}
