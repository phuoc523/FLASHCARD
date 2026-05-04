package com.flashcard.ui;

import com.flashcard.service.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private static final Color PRIMARY    = new Color(79, 70, 229);
    private static final Color BG_COLOR   = new Color(245, 245, 250);
    private static final Color CARD_COLOR = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BTN_FONT   = new Font("Segoe UI", Font.BOLD, 14);

    private JTextField     usernameField;
    private JTextField     emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JButton        registerBtn;
    private JLabel         errorLabel;

    public RegisterFrame() {
        setTitle("Flashcard App – Đăng ký");
        setSize(420, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(BG_COLOR);
        root.setBorder(new EmptyBorder(30, 40, 30, 40));
        setContentPane(root);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 230), 1, true),
                new EmptyBorder(30, 35, 30, 35)
        ));

        // Icon
        JLabel icon = new JLabel("✏️", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(icon);
        card.add(Box.createVerticalStrut(10));

        // Title
        JLabel titleLbl = new JLabel("Tạo tài khoản");
        titleLbl.setFont(TITLE_FONT);
        titleLbl.setForeground(PRIMARY);
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLbl);
        card.add(Box.createVerticalStrut(20));

        // Username
        card.add(makeLabel("Tên đăng nhập"));
        usernameField = makeInput();
        card.add(usernameField);
        card.add(Box.createVerticalStrut(14));

        // Email
        card.add(makeLabel("Email"));
        emailField = makeInput();
        card.add(emailField);
        card.add(Box.createVerticalStrut(14));

        // Password
        card.add(makeLabel("Mật khẩu"));
        passwordField = new JPasswordField();
        styleInput(passwordField);
        card.add(passwordField);
        card.add(Box.createVerticalStrut(14));

        // Confirm
        card.add(makeLabel("Xác nhận mật khẩu"));
        confirmField = new JPasswordField();
        styleInput(confirmField);
        card.add(confirmField);
        card.add(Box.createVerticalStrut(8));

        // Error
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(new Color(220, 38, 38));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(errorLabel);
        card.add(Box.createVerticalStrut(10));

        // Button
        registerBtn = new JButton("Đăng ký");
        registerBtn.setFont(BTN_FONT);
        registerBtn.setBackground(PRIMARY);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setOpaque(true);
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(registerBtn);
        card.add(Box.createVerticalStrut(16));

        // Link login
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        linkPanel.setOpaque(false);
        JLabel text = new JLabel("Đã có tài khoản?");
        text.setFont(LABEL_FONT);
        JButton loginLink = new JButton("Đăng nhập");
        loginLink.setFont(new Font("Segoe UI", Font.BOLD, 13));
        loginLink.setForeground(PRIMARY);
        loginLink.setBorderPainted(false);
        loginLink.setContentAreaFilled(false);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkPanel.add(text);
        linkPanel.add(loginLink);
        linkPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(linkPanel);

        root.add(card);

        // Events
        registerBtn.addActionListener(e -> doRegister());
        loginLink.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void doRegister() {
        String username = usernameField.getText().trim();
        String email    = emailField.getText().trim();   // ← THÊM MỚI
        String password = new String(passwordField.getPassword());
        String confirm  = new String(confirmField.getPassword());

        // ← THÊM email vào check rỗng
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        registerBtn.setEnabled(false);
        registerBtn.setText("Đang xử lý...");

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            protected String doInBackground() {
                return AuthService.getInstance()
                        .register(username, email, password, confirm); // ← THÊM email
            }

            protected void done() {
                try {
                    String result = get();
                    if (result == null) {
                        JOptionPane.showMessageDialog(
                                RegisterFrame.this,
                                "Đăng ký thành công!"
                        );
                        dispose();
                        new LoginFrame().setVisible(true);
                    } else {
                        errorLabel.setText(result);
                        registerBtn.setEnabled(true);
                        registerBtn.setText("Đăng ký");
                    }
                } catch (Exception ex) {
                    errorLabel.setText("Lỗi hệ thống");
                }
            }
        };
        worker.execute();
    }

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(LABEL_FONT);

        // 👇 THÊM 2 DÒNG NÀY
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        return lbl;
    }

    private JTextField makeInput() {
        JTextField tf = new JTextField();
        styleInput(tf);
        return tf;
    }

    private void styleInput(JComponent comp) {
        comp.setFont(INPUT_FONT);
        comp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        comp.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210)));
    }
}