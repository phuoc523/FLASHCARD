package com.flashcard.dao;

import com.flashcard.model.Card;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {

    public List<Card> getCardsByDeckId(int deckId) {
        List<Card> cards = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM cards WHERE deck_id = " + deckId
            );
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("id"));
                card.setDeckId(rs.getInt("deck_id"));
                card.setFront(rs.getString("front"));
                card.setBack(rs.getString("back"));
                card.setCreatedAt(rs.getDate("created_at").toLocalDate());
                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cards;
    }

    public Card getCardById(int id) {
        Card card = null;
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM cards WHERE id = " + id
            );
            if (rs.next()) {
                card = new Card();
                card.setId(rs.getInt("id"));
                card.setDeckId(rs.getInt("deck_id"));
                card.setFront(rs.getString("front"));
                card.setBack(rs.getString("back"));
                card.setCreatedAt(rs.getDate("created_at").toLocalDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card;
    }

    public void addCard(Card card) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();

            st.executeUpdate(
                    "INSERT INTO cards (deck_id, front, back) VALUES ("
                            + card.getDeckId() + ", '"
                            + card.getFront() + "', '"
                            + card.getBack() + "')"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCard(Card card) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(
                    "UPDATE cards SET front = '" + card.getFront()
                            + "', back = '" + card.getBack()
                            + "' WHERE id = " + card.getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCard(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(
                    "DELETE FROM cards WHERE id = " + id
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}