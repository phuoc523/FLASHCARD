package com.flashcard.ui;

import com.flashcard.service.AIService;

import javax.swing.*;
import java.awt.*;

public class AIChatFrame extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private AIService aiService;

    public AIChatFrame() {
        setTitle("AI Chat");
        setSize(400, 500);
        setLocationRelativeTo(null);

        aiService = new AIService();

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        inputField = new JTextField();

        JButton sendBtn = new JButton("Send");

        sendBtn.addActionListener(e -> sendMessage());

        setLayout(new BorderLayout());
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(sendBtn, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);
    }

    private void sendMessage() {
        String input = inputField.getText();
        chatArea.append("You: " + input + "\n");

        new Thread(() -> {
            String response = aiService.askAI(input);
            chatArea.append("AI: " + response + "\n");
        }).start();

        inputField.setText("");
    }
}