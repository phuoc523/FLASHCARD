package com.flashcard.ui;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {

    public SettingsFrame() {
        setTitle("Settings");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JCheckBox darkMode = new JCheckBox("Dark Mode");

        setLayout(new FlowLayout());
        add(darkMode);
    }
}