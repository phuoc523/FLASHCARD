package com.flashcard.ui;

import com.flashcard.model.Deck;
import com.flashcard.service.DeckService;
import com.flashcard.service.AuthService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DeckManagerFrame extends JFrame {

    private final DeckService deckService = new DeckService();
    private final JTable table;
    private final DefaultTableModel tableModel;

    public DeckManagerFrame() {
        setTitle("Deck Manager");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bảng danh sách deck
        tableModel = new DefaultTableModel(new String[]{"ID", "Tên", "Mô tả"}, 0);
        table = new JTable(tableModel);
        loadDecks();

        // Các nút
        JButton addBtn    = new JButton("Thêm");
        JButton editBtn   = new JButton("Sửa");
        JButton deleteBtn = new JButton("Xóa");
        JButton openBtn   = new JButton("Mở");

        addBtn.addActionListener(e -> addDeck());
        editBtn.addActionListener(e -> editDeck());
        deleteBtn.addActionListener(e -> deleteDeck());
        openBtn.addActionListener(e -> openDeck());

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(openBtn);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void loadDecks() {
        tableModel.setRowCount(0); // xóa dữ liệu cũ
        List<Deck> decks = deckService.getAllDecks();
        for (Deck deck : decks) {
            tableModel.addRow(new Object[]{
                    deck.getId(),
                    deck.getName(),
                    deck.getDescription()
            });
        }
    }

    private void addDeck() {
        String name = JOptionPane.showInputDialog(this, "Tên deck:");
        String desc = JOptionPane.showInputDialog(this, "Mô tả:");
        if (name != null && !name.isEmpty()) {
            Deck deck = new Deck();
            deck.setName(name);
            deck.setDescription(desc);
            deck.setUserId(AuthService.getInstance().getCurrentUser().getUserId()); // ← thêm dòng này
            deckService.addDeck(deck);
            loadDecks();
        }
    }

    private void editDeck() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn deck cần sửa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        Deck deck = deckService.getDeckById(id);

        String name = JOptionPane.showInputDialog(this, "Tên mới:", deck.getName());
        String desc = JOptionPane.showInputDialog(this, "Mô tả mới:", deck.getDescription());
        if (name != null && !name.isEmpty()) {
            deck.setName(name);
            deck.setDescription(desc);
            deckService.updateDeck(deck);
            loadDecks();
        }
    }

    private void deleteDeck() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn deck cần xóa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa deck này?");
        if (confirm == JOptionPane.YES_OPTION) {
            deckService.deleteDeck(id);
            loadDecks();
        }
    }

    private void openDeck() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn deck cần mở!");
            return;
        }
        int deckId = (int) tableModel.getValueAt(row, 0);
        new CardManagerFrame(deckId).setVisible(true);
    }
}