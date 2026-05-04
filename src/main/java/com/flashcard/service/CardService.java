package com.flashcard.service;

import com.flashcard.dao.CardDAO;
import com.flashcard.model.Card;
import java.util.List;

public class CardService {

    private final CardDAO cardDAO = new CardDAO();

    public List<Card> getCardsByDeckId(int deckId) {
        return cardDAO.getCardsByDeckId(deckId);
    }

    public Card getCardById(int id) {
        return cardDAO.getCardById(id);
    }

    public void addCard(Card card) {
        cardDAO.addCard(card);
    }

    public void updateCard(Card card) {
        cardDAO.updateCard(card);
    }

    public void deleteCard(int id) {
        cardDAO.deleteCard(id);
    }

    public void addCard(int deckId, String front, String back) {
        Card card = new Card();
        card.setDeckId(deckId);
        card.setFront(front);
        card.setBack(back);

        cardDAO.addCard(card);
    }
}