package com.flashcard.ui;

import com.flashcard.service.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private static final Color PRIMARY    = new Color(79, 70, 229);
    private static final Color BG_COLOR   = new Color(245, 245, 250);
    private static final Color CARD_COLOR = Color.WHITE;

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BTN_FONT   = new Font("Segoe UI", Font.BOLD, 14);

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JLabel errorLabel;

    public LoginFrame() {
        setTitle("Flashcard App – Đăng nhập");
        setSize(420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

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

        JLabel icon = new JLabel("📚", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(icon);

        card.add(Box.createVerticalStrut(10));

        JLabel titleLbl = new JLabel("Đăng nhập", SwingConstants.CENTER);
        titleLbl.setFont(TITLE_FONT);
        titleLbl.setForeground(PRIMARY);
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLbl);

        card.add(Box.createVerticalStrut(6));

        card.add(Box.createVerticalStrut(22));

        card.add(makeLabel("Tên đăng nhập hoặc Email"));
        card.add(Box.createVerticalStrut(4));
        usernameField = makeTextField("Nhập username...");
        card.add(usernameField);

        card.add(Box.createVerticalStrut(14));

        // Password
        card.add(makeLabel("Mật khẩu"));
        card.add(Box.createVerticalStrut(4));
        passwordField = new JPasswordField();
        styleInput(passwordField);
        card.add(passwordField);

        card.add(Box.createVerticalStrut(6));

        // Error
        errorLabel = new JLabel(" ");
        errorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        errorLabel.setForeground(new Color(220, 38, 38));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(errorLabel);

        card.add(Box.createVerticalStrut(6));

        // Button
        // Button
        loginBtn = new JButton("Đăng nhập") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // gradient tím giống ảnh
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(99, 102, 241),
                        getWidth(), 0, new Color(79, 70, 229)
                );

                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();

                super.paintComponent(g);
            }
        };

        loginBtn.setFont(BTN_FONT);
        loginBtn.setForeground(Color.WHITE);

// ❌ bỏ dòng này vì đã custom paint
// loginBtn.setBackground(PRIMARY);

        loginBtn.setContentAreaFilled(false); // 👈 QUAN TRỌNG
        loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);

        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(loginBtn);

        card.add(Box.createVerticalStrut(16));

        // Register link
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        linkPanel.setOpaque(false);

        JLabel noAccLbl = new JLabel("Chưa có tài khoản?");
        noAccLbl.setFont(LABEL_FONT);

        JButton registerLink = new JButton("Đăng ký");
        registerLink.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registerLink.setForeground(PRIMARY);
        registerLink.setBorderPainted(false);
        registerLink.setContentAreaFilled(false);

        linkPanel.add(noAccLbl);
        linkPanel.add(registerLink);
        linkPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(linkPanel);

        root.add(card);

        // Events
        loginBtn.addActionListener(e -> doLogin());

        KeyAdapter enterKey = new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) doLogin();
            }
        };

        usernameField.addKeyListener(enterKey);
        passwordField.addKeyListener(enterKey);

        registerLink.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    private void doLogin() {
        String input    = usernameField.getText().trim(); // ← đổi userName → input
        String password = new String(passwordField.getPassword());

        if (input.isEmpty() || password.isEmpty()) {
            showError("Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        loginBtn.setEnabled(false);
        loginBtn.setText("Đang đăng nhập...");

        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {

            protected Boolean doInBackground() {
                return AuthService.getInstance().login(input, password); // ← truyền input
            }

            protected void done() {
                try {
                    if (get()) {
                        dispose();
                        new DashboardFrame().setVisible(true);
                    } else {
                        showError("Sai thông tin đăng nhập hoặc mật khẩu");
                        loginBtn.setEnabled(true);
                        loginBtn.setText("Đăng nhập");
                    }
                } catch (Exception ex) {
                    showError("Lỗi kết nối");
                    loginBtn.setEnabled(true);
                    loginBtn.setText("Đăng nhập");
                }
            }
        };
        worker.execute();
    }

    private void showError(String msg) {
        errorLabel.setText(msg);
    }

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(LABEL_FONT);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT); // căn giữa theo layout
        lbl.setHorizontalAlignment(SwingConstants.CENTER); // 👈 căn giữa chữ
        return lbl;
    }

    private JTextField makeTextField(String placeholder) {
        JTextField tf = new JTextField();
        styleInput(tf);
        tf.setToolTipText(placeholder);
        return tf;
    }

    private void styleInput(JComponent comp) {
        comp.setFont(INPUT_FONT);
        comp.setMaximumSize(new Dimension(260, 38)); // 👈 chỉnh width
        comp.setAlignmentX(Component.CENTER_ALIGNMENT); // 👈 căn giữa

        if (comp instanceof JTextField) {
            ((JTextField) comp).setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 210), 1, true),
                    new EmptyBorder(4, 10, 4, 10)
            ));
        }
    }
}

class GradientButton extends JButton {

    public GradientButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gp = new GradientPaint(
                0, 0, new Color(99, 102, 241),   // màu trái
                getWidth(), 0, new Color(79, 70, 229) // màu phải
        );

        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintComponent(g);
    }
}