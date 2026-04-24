-- Sample data for Flashcard App

INSERT INTO users (username, email, password_hash)
VALUES ('demo', 'demo@example.com', 'passwordhash');

INSERT INTO decks (user_id, name, description)
VALUES (1, 'Sample Deck', 'A starter deck for demo cards.');

INSERT INTO cards (deck_id, front, back)
VALUES
(1, 'What is spaced repetition?', 'A learning technique that spaces review sessions.'),
(1, 'What is a flashcard?', 'A study card with a question on one side and answer on the other.');
