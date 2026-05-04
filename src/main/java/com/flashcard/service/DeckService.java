package com.flashcard.service;

import com.flashcard.dao.DeckDAO;
import com.flashcard.model.Deck;
import java.util.List;
public class DeckService {

    private final DeckDAO deckDAO = new DeckDAO();

    public Deck getDeckById(int id) {
        return deckDAO.getDeckById(id);
    }

    public void addDeck(Deck deck) {
        deckDAO.addDeck(deck);
    }

    public void updateDeck(Deck deck) {
        deckDAO.updateDeck(deck);
    }

    public void deleteDeck(int id) {
        deckDAO.deleteDeck(id);
    }
    public List<Deck> getAllDecks() {
        return deckDAO.getAllDecks();
    }
}