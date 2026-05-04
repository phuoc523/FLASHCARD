-- =============================================================
-- FLASHCARD APP — DATABASE SCHEMA
-- Khớp hoàn toàn với code FLASHCARD1
-- MySQL | Database: flashcard
-- =============================================================

CREATE DATABASE IF NOT EXISTS flashcard;
USE flashcard;

-- =============================================================
-- 1. USERS
-- Dùng bởi: UserDAO → username, email, password_hash
-- =============================================================
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(100) NOT NULL UNIQUE,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role          ENUM('USER', 'ADMIN') DEFAULT 'USER',
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP
    );

-- =============================================================
-- 2. DECKS
-- Dùng bởi: DeckDAO → id, name, description, user_id
-- =============================================================
CREATE TABLE IF NOT EXISTS decks (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE
    );

-- =============================================================
-- 3. CARDS
-- Dùng bởi: CardDAO → id, deck_id, front, back, created_at
--           StatisticsDAO → cards WHERE user_id (qua deck)
-- =============================================================
CREATE TABLE IF NOT EXISTS cards (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    deck_id    INT NOT NULL,
    front      TEXT NOT NULL,
    back       TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (deck_id) REFERENCES decks(id)
    ON DELETE CASCADE
    );

-- =============================================================
-- 4. STUDY_PROGRESS
-- Dùng bởi: StudyProgressDAO
--   → progressID, userID, flashcardID,
--     repetition, interval, easeFactor,
--     nextReviewDate, lastReviewed
-- =============================================================
CREATE TABLE IF NOT EXISTS Study_Progress (
    progressID     INT AUTO_INCREMENT PRIMARY KEY,
    userID         INT NOT NULL,
    flashcardID    INT NOT NULL,
    repetition     INT DEFAULT 0,
    `interval`     INT DEFAULT 1,
    easeFactor     DOUBLE DEFAULT 2.5,
    nextReviewDate DATE,
    lastReviewed   DATE,

    UNIQUE (userID, flashcardID),

    FOREIGN KEY (userID)      REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (flashcardID) REFERENCES cards(id) ON DELETE CASCADE
    );

-- =============================================================
-- 5. STUDYLOG
-- Dùng bởi: StudyLogDAO
--   → userID, flashcardID, quality,
--     reviewTime, intervalAfter, easeFactorAfter
-- =============================================================
CREATE TABLE IF NOT EXISTS StudyLog (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    userID          INT NOT NULL,
    flashcardID     INT NOT NULL,
    quality         INT NOT NULL,
    reviewTime      DATETIME NOT NULL,
    intervalAfter   INT NOT NULL,
    easeFactorAfter DOUBLE NOT NULL,

    FOREIGN KEY (userID)      REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (flashcardID) REFERENCES cards(id) ON DELETE CASCADE
    );

-- =============================================================
-- 6. STATISTICS
-- Dùng bởi: StatisticsDAO
--   → total_cards (COUNT từ cards WHERE user_id)
--   (bảng này dùng để cache thống kê, không bắt buộc)
-- =============================================================
CREATE TABLE IF NOT EXISTS statistics (
    user_id        INT PRIMARY KEY,
    total_cards    INT DEFAULT 0,
    learned_cards  INT DEFAULT 0,
    study_sessions INT DEFAULT 0,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

-- =============================================================
-- INDEX (Tăng tốc truy vấn)
-- =============================================================
CREATE INDEX idx_cards_deck         ON cards(deck_id);
CREATE INDEX idx_progress_user_card ON Study_Progress(userID, flashcardID);
CREATE INDEX idx_studylog_user      ON StudyLog(userID);
CREATE INDEX idx_decks_user         ON decks(user_id);

-- =============================================================
-- SAMPLE DATA
-- =============================================================
INSERT INTO users (username, email, password_hash, role) VALUES
('admin', 'admin@gmail.com', 'admin123', 'ADMIN'),
('user1', 'user1@gmail.com', '123456',   'USER');

INSERT INTO decks (user_id, name, description) VALUES
(2, 'Basic English', 'Từ vựng tiếng Anh cơ bản'),
(2, 'TOEIC 600',     'Từ vựng TOEIC level 600');

INSERT INTO cards (deck_id, front, back) VALUES
(1, 'apple', 'quả táo'),
(1, 'dog',   'con chó'),
(1, 'book',  'quyển sách'),
(2, 'acquire',  'đạt được'),
(2, 'allocate', 'phân bổ');

INSERT INTO statistics (user_id, total_cards, learned_cards, study_sessions) VALUES
(2, 5, 0, 0);