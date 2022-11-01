import javax.swing.*;

public class Panel {
    JPanel panel;
    JFrame frame;

    public Panel() {
        panel = new JPanel();
        frame = new JFrame();
        frame.add(panel);
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Panel();
    }
}
