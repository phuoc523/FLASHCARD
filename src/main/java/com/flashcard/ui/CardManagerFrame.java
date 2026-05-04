package com.flashcard.ui;

import com.flashcard.model.Card;
import com.flashcard.service.CardService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CardManagerFrame extends JFrame {

    private final CardService cardService = new CardService();
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final int deckId;

    public CardManagerFrame(int deckId) {
        this.deckId = deckId;

        setTitle("Quản lý Card");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Bảng danh sách card
        tableModel = new DefaultTableModel(new String[]{"ID", "Mặt trước", "Mặt sau", "Ngày tạo"}, 0);
        table = new JTable(tableModel);

        // Các nút
        JButton addBtn    = new JButton("Thêm");
        JButton editBtn   = new JButton("Sửa");
        JButton deleteBtn = new JButton("Xóa");

        addBtn.addActionListener(e -> addCard());
        editBtn.addActionListener(e -> editCard());
        deleteBtn.addActionListener(e -> deleteCard());

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        loadCards();
    }

    private void loadCards() {
        tableModel.setRowCount(0);
        List<Card> cards = cardService.getCardsByDeckId(deckId);
        for (Card card : cards) {
            tableModel.addRow(new Object[]{
                    card.getId(),
                    card.getFront(),
                    card.getBack(),
                    card.getCreatedAt()
            });
        }
    }

    private void addCard() {
        String front = JOptionPane.showInputDialog(this, "Mặt trước:");
        String back  = JOptionPane.showInputDialog(this, "Mặt sau:");
        if (front != null && !front.isEmpty()) {
            Card card = new Card();
            card.setDeckId(deckId);
            card.setFront(front);
            card.setBack(back);
            card.setCreatedAt(LocalDate.now());
            cardService.addCard(card);
            loadCards();
        }
    }

    private void editCard() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn card cần sửa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        Card card = cardService.getCardById(id);

        String front = JOptionPane.showInputDialog(this, "Mặt trước mới:", card.getFront());
        String back  = JOptionPane.showInputDialog(this, "Mặt sau mới:", card.getBack());
        if (front != null && !front.isEmpty()) {
            card.setFront(front);
            card.setBack(back);
            cardService.updateCard(card);
            loadCards();
        }
    }

    private void deleteCard() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn card cần xóa!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa card này?");
        if (confirm == JOptionPane.YES_OPTION) {
            cardService.deleteCard(id);
            loadCards();
        }
    }
}