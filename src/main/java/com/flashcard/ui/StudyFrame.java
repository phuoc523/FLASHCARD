import com.flashcard.service.StudyService;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class StudyFrame extends JFrame {

    private StudyService service;
    private int userID;
    private int flashcardID;

    public StudyFrame(Connection conn, int userID, int flashcardID) {

        this.userID = userID;
        this.flashcardID = flashcardID;
        this.service = new StudyService(conn);

        setTitle("Study Flashcard");
        setSize(400, 250);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Bạn nhớ từ này ở mức nào?");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton remembered = new JButton("Đã nhớ");
        JButton notRemembered = new JButton("Chưa nhớ");

        remembered.addActionListener(e -> handleReview(4));
        notRemembered.addActionListener(e -> handleReview(1));

        JPanel panel = new JPanel();
        panel.add(remembered);
        panel.add(notRemembered);

        add(label, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void handleReview(int quality) {
        try {
            service.reviewFlashcard(userID, flashcardID, quality);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống!");
        }
    }
}