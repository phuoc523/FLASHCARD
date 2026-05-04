package com.flashcard.ui;

import com.flashcard.model.User;
import com.flashcard.service.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class DashboardFrame extends JFrame {

    private static final Color SIDEBAR_BG  = new Color(30, 27, 75);
    private static final Color SIDEBAR_HVR = new Color(55, 48, 120);
    private static final Color ACTIVE_BG   = new Color(79, 70, 229);
    private static final Color CONTENT_BG  = new Color(245, 245, 250);
    private static final Color TEXT_WHITE  = Color.WHITE;
    private static final Color TEXT_MUTED  = new Color(180, 180, 210);

    private static final Font MENU_FONT   = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 22);
    private static final Font CARD_TITLE  = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font CARD_VALUE  = new Font("Segoe UI", Font.BOLD, 32);

    private JPanel  contentPanel;
    private JButton activeMenuBtn;

    public DashboardFrame() {
        setTitle("Flashcard App – Dashboard");
        setSize(950, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 500));
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        add(buildSidebar(), BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(CONTENT_BG);
        add(contentPanel, BorderLayout.CENTER);

        showHome();
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(210, 0));

        // Logo
        JLabel logoLbl = new JLabel("📚 FlashCard", SwingConstants.CENTER);
        logoLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logoLbl.setForeground(TEXT_WHITE);
        logoLbl.setBorder(new EmptyBorder(24, 16, 20, 16));
        logoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logoLbl);

        sidebar.add(new JSeparator());
        sidebar.add(Box.createVerticalStrut(10));

        // Menu
        String[][] menus = {
                {"🏠", "Trang chủ"},
                {"🃏", "Bộ Flashcard"},
                {"📖", "Học ngay"},
                {"📝", "Kiểm tra"},
                {"📊", "Thống kê"},
                {"🤖", "Hỗ trợ AI"}
        };

        for (String[] menu : menus) {
            JButton btn = makeMenuBtn(menu[0] + "  " + menu[1]);
            String action = menu[1];

            btn.addActionListener(e -> handleMenuClick(btn, action));
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(2));

            if (action.equals("Trang chủ")) setActive(btn);
        }

        sidebar.add(Box.createVerticalGlue());

        User user = AuthService.getInstance().getCurrentUser();
        String displayName = user != null
                ? user.getUserName() + " (" + user.getEmail() + ")"
                : "unknown";

        JLabel userLbl = new JLabel("👤 " + displayName);
        userLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userLbl.setForeground(TEXT_MUTED);
        userLbl.setBorder(new EmptyBorder(10, 16, 8, 16));
        sidebar.add(userLbl);

        // Logout
        JButton logoutBtn = makeMenuBtn("🚪  Đăng xuất");
        logoutBtn.setForeground(new Color(252, 165, 165));
        logoutBtn.addActionListener(e -> doLogout());
        sidebar.add(logoutBtn);
        sidebar.add(Box.createVerticalStrut(16));

        return sidebar;
    }

    private JButton makeMenuBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(MENU_FONT);
        btn.setForeground(TEXT_MUTED);
        btn.setBackground(SIDEBAR_BG);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 20, 10, 16));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btn != activeMenuBtn) {
                    btn.setBackground(SIDEBAR_HVR);
                    btn.setForeground(TEXT_WHITE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (btn != activeMenuBtn) {
                    btn.setBackground(SIDEBAR_BG);
                    btn.setForeground(TEXT_MUTED);
                }
            }
        });

        return btn;
    }

    private void setActive(JButton btn) {
        if (activeMenuBtn != null) {
            activeMenuBtn.setBackground(SIDEBAR_BG);
            activeMenuBtn.setForeground(TEXT_MUTED);
        }
        activeMenuBtn = btn;
        btn.setBackground(ACTIVE_BG);
        btn.setForeground(TEXT_WHITE);
    }

    private void handleMenuClick(JButton btn, String action) {
        setActive(btn);
        contentPanel.removeAll();

        switch (action) {
//            case "Trang chủ" -> showHome();
//            default -> showComingSoon(action);
            case "Trang chủ" -> showHome();
            case "Bộ Flashcard" -> showAddCard();
            case "Hỗ trợ AI" -> showAI();
            default -> showComingSoon(action);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showHome() {
        contentPanel.removeAll();

        JPanel home = new JPanel(new BorderLayout());
        home.setBackground(CONTENT_BG);
        home.setBorder(new EmptyBorder(30, 30, 30, 30));

        User user = AuthService.getInstance().getCurrentUser();
        String name = user != null ? user.getUserName() : "bạn";

        JLabel headerLbl = new JLabel("Chào mừng, " + name + "! 👋");
        headerLbl.setFont(HEADER_FONT);
        home.add(headerLbl, BorderLayout.NORTH);

        // Stats grid
        JPanel grid = new JPanel(new GridLayout(2, 2, 16, 16));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(20, 0, 20, 0));

        grid.add(makeCard("🃏 Bộ thẻ",   "--"));
        grid.add(makeCard("📝 Thẻ học",  "--"));
        grid.add(makeCard("🔥 Cần ôn",   "--"));
        grid.add(makeCard("✅ Kiểm tra", "--"));

        home.add(grid, BorderLayout.CENTER);

        contentPanel.add(home, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel makeCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel t = new JLabel(title);
        t.setFont(CARD_TITLE);

        JLabel v = new JLabel(value);
        v.setFont(CARD_VALUE);

        card.add(t, BorderLayout.NORTH);
        card.add(v, BorderLayout.CENTER);

        return card;
    }

    private void showComingSoon(String feature) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CONTENT_BG);

        JLabel lbl = new JLabel(feature + " đang phát triển...");
        lbl.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        panel.add(lbl);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    private void doLogout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn đăng xuất?",
                "Đăng xuất",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            AuthService.getInstance().logout();
            dispose();
            new LoginFrame().setVisible(true);
        }
    }
//    private void showAI() {
//        contentPanel.removeAll();
//
//        JPanel panel = new JPanel(new BorderLayout(10, 10));
//        panel.setBackground(CONTENT_BG);
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        // ======================
//        // INPUT + OUTPUT SPLIT
//        // ======================
//        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//        split.setResizeWeight(0.5);
//
//        // INPUT
//        JTextArea inputArea = new JTextArea();
//        inputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        inputArea.setLineWrap(true);
//        inputArea.setWrapStyleWord(true);
//
//        JScrollPane inputScroll = new JScrollPane(inputArea);
//
//        // OUTPUT
//        JTextArea outputArea = new JTextArea();
//        outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        outputArea.setLineWrap(true);
//        outputArea.setWrapStyleWord(true);
//        outputArea.setEditable(false);
//
//        JScrollPane outputScroll = new JScrollPane(outputArea);
//
//        split.setTopComponent(inputScroll);
//        split.setBottomComponent(outputScroll);
//
//        // ======================
//        // BUTTON
//        // ======================
////        JButton askBtn = new JButton("✨ Hỏi AI");
////        askBtn.setBackground(new Color(79, 70, 229));
////        askBtn.setForeground(Color.WHITE);
////        askBtn.setFocusPainted(false);
//
//        JButton askBtn = new JButton("✨ Hỏi AI");
//
//// màu tím giống ảnh
//        askBtn.setBackground(new Color(99, 102, 241));
//        askBtn.setForeground(Color.WHITE);
//
//// bỏ style mặc định xấu
//        askBtn.setFocusPainted(false);
//        askBtn.setBorderPainted(false);
//        askBtn.setContentAreaFilled(true);
//        askBtn.setOpaque(true);
//
//// bo góc giả lập (không phải gradient thật)
//        askBtn.setBorder(BorderFactory.createLineBorder(new Color(99,102,241), 1, true));
//
//// cursor đẹp
//        askBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        askBtn.addActionListener(e -> {
//            String prompt = inputArea.getText().trim();
//
//            if (prompt.isEmpty()) {
//                outputArea.setText("Vui lòng nhập nội dung!");
//                return;
//            }
//
//            outputArea.setText("Đang hỏi AI...");
//
//            new SwingWorker<String, Void>() {
//                protected String doInBackground() {
//                    return new com.flashcard.service.AIService().askAI(prompt);
//                }
//
//                protected void done() {
//                    try {
//                        outputArea.setText(get());
//                    } catch (Exception ex) {
//                        outputArea.setText("Lỗi AI!");
//                    }
//                }
//            }.execute();
//        });
//
//        // ======================
//        // LAYOUT
//        // ======================
//        panel.add(split, BorderLayout.CENTER);
//        panel.add(askBtn, BorderLayout.SOUTH);
//
//        contentPanel.add(panel, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//    }

private void showAI() {
    contentPanel.removeAll();

    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBackground(CONTENT_BG);
    panel.setBorder(new EmptyBorder(20, 20, 20, 20));

    // ======================
    // INPUT + OUTPUT SPLIT
    // ======================
    JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    split.setResizeWeight(0.5);

    // INPUT
    JTextArea inputArea = new JTextArea();
    inputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    inputArea.setLineWrap(true);
    inputArea.setWrapStyleWord(true);
    inputArea.setBorder(new EmptyBorder(10,10,10,10));

    JScrollPane inputScroll = new JScrollPane(inputArea);

    // OUTPUT
    JTextArea outputArea = new JTextArea();
    outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    outputArea.setLineWrap(true);
    outputArea.setWrapStyleWord(true);
    outputArea.setEditable(false);
    outputArea.setBorder(new EmptyBorder(10,10,10,10));

    JScrollPane outputScroll = new JScrollPane(outputArea);

    split.setTopComponent(inputScroll);
    split.setBottomComponent(outputScroll);

    // ======================
    // BUTTON (ĐẸP + CLICK OK)
    // ======================
    JButton askBtn = new JButton("✨ Hỏi AI");

    askBtn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
    askBtn.setForeground(Color.WHITE);
    askBtn.setBackground(new Color(99, 102, 241));

    askBtn.setFocusPainted(false);
    askBtn.setBorderPainted(false);
    askBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // bo góc mềm
    askBtn.setBorder(new EmptyBorder(12, 20, 12, 20));

    // hiệu ứng hover
    askBtn.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            askBtn.setBackground(new Color(79, 70, 229));
        }
        public void mouseExited(MouseEvent e) {
            askBtn.setBackground(new Color(99, 102, 241));
        }
    });

    // ======================
    // ACTION (FIX CLICK)
    // ======================
    askBtn.addActionListener(e -> {
        String prompt = inputArea.getText().trim();

        if (prompt.isEmpty()) {
            outputArea.setText("⚠️ Vui lòng nhập nội dung!");
            return;
        }

        askBtn.setEnabled(false);
        outputArea.setText("⏳ Đang hỏi AI...");

        new SwingWorker<String, Void>() {
            protected String doInBackground() {
                return new com.flashcard.service.AIService().askAI(prompt);
            }

            protected void done() {
                try {
                    outputArea.setText(get());
                } catch (Exception ex) {
                    outputArea.setText("❌ Lỗi AI!");
                }
                askBtn.setEnabled(true);
            }
        }.execute();
    });

    // ======================
    // WRAPPER (THU HẸP 2 BÊN)
    // ======================
    JPanel btnWrapper = new JPanel(new BorderLayout());
    btnWrapper.setBackground(CONTENT_BG);
    btnWrapper.setBorder(new EmptyBorder(10, 100, 10, 100)); // chỉnh số này nếu muốn rộng/hẹp

    btnWrapper.add(askBtn, BorderLayout.CENTER);

    // ======================
    // ADD
    // ======================
    panel.add(split, BorderLayout.CENTER);
    panel.add(btnWrapper, BorderLayout.SOUTH);

    contentPanel.add(panel, BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
}
    private void showAddCard() {
        contentPanel.removeAll();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(CONTENT_BG);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== TITLE =====
        JLabel title = new JLabel("Thêm Flashcard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // ===== DECK ID =====
        gbc.gridy++;
        panel.add(new JLabel("Deck ID:"), gbc);

        JTextField deckField = new JTextField("1");
        gbc.gridx = 1;
        panel.add(deckField, gbc);

        // ===== FRONT =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Front:"), gbc);

        JTextArea frontArea = new JTextArea(3, 20);
        gbc.gridx = 1;
        panel.add(new JScrollPane(frontArea), gbc);

        // ===== BACK =====
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Back:"), gbc);

        JTextArea backArea = new JTextArea(3, 20);
        gbc.gridx = 1;
        panel.add(new JScrollPane(backArea), gbc);

        // ===== BUTTON =====
        JButton saveBtn = new JButton("Lưu Flashcard");
        saveBtn.setBackground(new Color(79, 70, 229));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(saveBtn, gbc);

        // ===== ACTION =====
        saveBtn.addActionListener(e -> {
            try {
                int deckId = Integer.parseInt(deckField.getText());
                String front = frontArea.getText();
                String back = backArea.getText();

                new com.flashcard.service.CardService()
                        .addCard(deckId, front, back);

                JOptionPane.showMessageDialog(this, "Thêm thành công!");

                frontArea.setText("");
                backArea.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        });

        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}