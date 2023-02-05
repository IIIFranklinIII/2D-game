package mainPackage;

import characters.Entity;
import characters.Player;
import environment.Environment;
import object.ObjectManager;
import preset.TileManager;

import javax.swing.*;
import java.awt.*;

public class GameConfig extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 13;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public GameControl keyH = new GameControl(this);
    Thread gameThread;
    public CollisionChecker checker = new CollisionChecker(this);
    public CharactersSetter aSetter = new CharactersSetter(this);
    public UI ui = new UI(this);
    public Events eHandler = new Events(this);
    public Player player = new Player(this, keyH);
    Environment eManager = new Environment(this);
    public ObjectManager[] obj = new ObjectManager[10];
    public Entity[] npc = new Entity[10];

    public int gameState;
    public final int titleState = 0;
    public int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int introState = 4;

    public GameConfig() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setNPC();
        eManager.setup();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            player.update();

            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == titleState) {
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);

            for (ObjectManager superObject : obj) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }

            player.draw(g2);
            eManager.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }
    }
}
