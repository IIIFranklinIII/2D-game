package mainPackage;

import characters.Entity;
import object.OBJ_Heart;
import object.ObjectManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI extends Entity {
    GameConfig gameConfig;
    Graphics2D g2;
    Font verdana_40, arial_80B, arial_30B;
    public BufferedImage heart_full, heart_half, heart_blank;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int introScreenState = 0;
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GameConfig gp) {
        super(gp);
        this.gameConfig = gp;

        verdana_40 = new Font("Verdana",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        arial_30B = new Font("Arial",Font.BOLD,30);

        ObjectManager heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_30B);
        g2.setColor(Color.white);


        if (gameConfig.gameState == gameConfig.playState) {
            playTime += (double) 1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gameConfig.tileSize*10,65);
        }
        if(gameConfig.gameState == gameConfig.titleState) {
            drawTitleScreen();
        }
        if(gameConfig.gameState == gameConfig.introState) {
            drawIntroScreen();
        }
        if(gameConfig.gameState == gameConfig.playState) {
            drawPlayerLife();
        }
        if(gameConfig.gameState == gameConfig.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        if(gameConfig.gameState == gameConfig.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
    }

    public void drawPlayerLife() {
        if(gameConfig.gameState == gameConfig.playState) {

            int x = gameConfig.tileSize / 2;
            int y = gameConfig.tileSize / 2;
            int i = 0;

            while(i < gameConfig.player.maxLife / 2) {
                g2.drawImage(heart_blank,x,y,null);
                i++;
                x += gameConfig.tileSize;
            }

            x = gameConfig.tileSize / 2;
            y = gameConfig.tileSize / 2;
            i = 0;

            while(i < gameConfig.player.life) {
                g2.drawImage(heart_half,x,y,null);
                i++;
                if(i < gameConfig.player.life) {
                    g2.drawImage(heart_full,x,y,null);
                }
                i++;
                x += gameConfig.tileSize;
            }
        }
    }

    public void drawDialogueScreen() {
        int x = gameConfig.tileSize * 2;
        int y = gameConfig.tileSize / 2;
        int width = gameConfig.screenWidth - (gameConfig.tileSize * 4);
        int height = gameConfig.tileSize * 5;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gameConfig.tileSize;
        y += gameConfig.tileSize;

        for(String line:currentDialogue.split("\n")) {
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 124);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(225,225,225);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public void drawIntroScreen() {
        Color c = new Color(0,0,0, 169);
        g2.setColor(c);
        g2.fillRoundRect(gameConfig.tileSize * 2, gameConfig.tileSize, gameConfig.screenWidth - (gameConfig.tileSize * 4), gameConfig.tileSize * 9,35,35);

        if (introScreenState == 1) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

            String text = "How bad your things could be?";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text, x, y);
        } if (introScreenState == 2) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            String text = "That question fucked around my mind.";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text, x, y);
        } else if (introScreenState == 3) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            String text = "Actually I got the answer...";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text, x, y);
        } else if (introScreenState == 4) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            String text = "Something strange helped me.";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text, x, y);
        } else if (introScreenState == 5) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            String text = "My name is Jack Copperfield.";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text, x, y);
        } else if (introScreenState == 6) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            String text = "And this is my story...";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text, x, y);
        }
    }

    public void drawTitleScreen() {
        if(titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0, gameConfig.screenWidth, gameConfig.screenHeight);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "8 Miles Away";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;

            g2.setColor(new Color(96, 96, 96));
            g2.drawString(text,x + 5,y + 5);
            g2.setColor(Color.white);
            g2.drawString(text,x,y);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45F));

            text = "New Game";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize * 3;
            g2.drawString(text,14,y);
            if(commandNum == 0) {
                g2.drawString("[]",250,y);
            }

            text = "Select An Episode";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize;
            g2.drawString(text,14,y);
            if(commandNum == 1) {
                g2.drawString("[]",410,y);
            }

            text = "Quit";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize;
            g2.drawString(text,14,y);
            if(commandNum == 2) {
                g2.drawString("[]",110,y);
            }
        }
        // Class selection screen
        else if(titleScreenState == 1) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your character:";
            int x = getXForCenteredText(text);
            int y = gameConfig.tileSize * 3;
            g2.drawString(text,x,y);

            text = "Treasure hunter";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 0) {
                g2.drawString("[]",x- gameConfig.tileSize,y);
            }

            text = "Sheriff";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1) {
                g2.drawString("[]",x- gameConfig.tileSize,y);
            }

            text = "Developer of this game";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 2) {
                g2.drawString("[]",x- gameConfig.tileSize,y);
            }

            text = "Back";
            x = getXForCenteredText(text);
            y += gameConfig.tileSize * 3;
            g2.drawString(text,x,y);
            if(commandNum == 3) {
                g2.drawString("[]",x- gameConfig.tileSize,y);
            }
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gameConfig.screenHeight / 2;

        g2.drawString(text,x,y);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gameConfig.screenWidth / 2 - length / 2;

        return x;
    }
}
