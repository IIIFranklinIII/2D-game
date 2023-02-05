package characters;

import mainPackage.GameConfig;
import mainPackage.GameControl;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GameConfig gp;
    GameControl keyH;

    public final int screenX;
    public final int screenY;

    public Player(GameConfig gp, GameControl keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 30;
        solidArea.y = 37;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 32;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        worldX = gp.tileSize * 6;
        worldY = gp.tileSize * 4;
        speed = 3;
        direction = "down";

        // Player status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("player/playerUp1");
        up2 = setup("player/playerUp2");
        down1 = setup("player/playerDown1");
        down2 = setup("player/playerDown2");
        left1 = setup("player/playerLeft1");
        left2 = setup("player/playerLeft2");
        right1 = setup("player/playerRight1");
        right2 = setup("player/playerRight2");
    }

    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed) {
                direction = "up";
            } else if(keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.checker.checkTile(this);

            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            gp.eHandler.checkEvent();
            gp.keyH.enterPressed = false;

            if(!collisionOn) {
                switch(direction) {
                    case  "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void interactNPC(int i) {
        if(i != 999) {
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        int x = screenX;
        int y = screenY;

        if(screenX > worldX) {
            x = worldX;
        }
        if(screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }

        g2.drawImage(image,x,y,null);
    }
}
