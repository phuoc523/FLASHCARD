package com.flashcard.algorithm;

import com.flashcard.model.StudyProgress; // ✅ FIX
import java.time.LocalDate;

public class SM2Algorithm {

    public static void update(StudyProgress progress, int quality) {

        if (quality < 3) {
            progress.setRepetition(0);
            progress.setInterval(1);
        } else {

            progress.setRepetition(progress.getRepetition() + 1);

            if (progress.getRepetition() == 1)
                progress.setInterval(1);
            else if (progress.getRepetition() == 2)
                progress.setInterval(6);
            else
                progress.setInterval(
                        (int) Math.round(progress.getInterval() * progress.getEaseFactor()));
        }

        double ef = progress.getEaseFactor();
        ef = ef + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));

        if (ef < 1.3) ef = 1.3;

        progress.setEaseFactor(ef);
        progress.setNextReviewDate(LocalDate.now().plusDays(progress.getInterval()));
        progress.setLastReviewed(LocalDate.now());
    }
}