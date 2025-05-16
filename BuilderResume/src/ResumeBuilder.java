import javax.swing.*;

public class ResumeBuilder {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ResumeFrame frame = new ResumeFrame();
            frame.setVisible(true);
        });
    }
}
