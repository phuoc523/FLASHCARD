package com.flashcard.dao;

import com.flashcard.model.Deck;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DeckDAO {

    public Deck getDeckById(int id) {
        Deck deck = null;
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM decks WHERE id = " + id
            );
            if (rs.next()) {
                deck = new Deck();
                deck.setId(rs.getInt("id"));
                deck.setName(rs.getString("name"));
                deck.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deck;
    }

    public void addDeck(Deck deck) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(
                    "INSERT INTO decks (user_id, name, description) VALUES ("
                            + deck.getUserId() + ", '" + deck.getName() + "', '" + deck.getDescription() + "')"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDeck(Deck deck) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(
                    "UPDATE decks SET name = '" + deck.getName()
                            + "', description = '" + deck.getDescription()
                            + "' WHERE id = " + deck.getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDeck(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(
                    "DELETE FROM decks WHERE id = " + id
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Deck> getAllDecks() {
        List<Deck> decks = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM decks");
            while (rs.next()) {
                Deck deck = new Deck();
                deck.setId(rs.getInt("id"));
                deck.setName(rs.getString("name"));
                deck.setDescription(rs.getString("description"));
                decks.add(deck);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decks;
    }

}