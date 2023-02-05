package mainPackage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GameConfig gameConfig = new GameConfig();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gameConfig);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameConfig.setupGame();
        gameConfig.startGameThread();
    }
}