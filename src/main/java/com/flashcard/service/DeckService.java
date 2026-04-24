package com.flashcard.service;

import com.flashcard.dao.DeckDAO;
import com.flashcard.model.Deck;

import java.util.List;

public class DeckService {
    private final DeckDAO deckDAO = new DeckDAO();

    public List<Deck> getDecks(int userId) {
        return deckDAO.findAllByUserId(userId);
    }

    public void saveDeck(Deck deck) {
        deckDAO.save(deck);
    }
}
