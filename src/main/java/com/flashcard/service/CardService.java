package com.flashcard.service;

import com.flashcard.dao.CardDAO;
import com.flashcard.model.Card;

import java.util.List;

public class CardService {
    private final CardDAO cardDAO = new CardDAO();

    public List<Card> getCards(int deckId) {
        return cardDAO.findByDeckId(deckId);
    }

    public void saveCard(Card card) {
        cardDAO.save(card);
    }
}
