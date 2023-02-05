package mainPackage;

import java.awt.*;

public class Events {
    GameConfig gp;
    Rectangle eventRect;
    int eventRectDefaultX,eventRectDefaultY;

    public Events(GameConfig gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 6;
        eventRect.height = 6;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if(hit(14, 18, "right")) {damagePit(gp.dialogueState);}
        if(hit(18, 10, "down")) {teleport(gp.dialogueState);}
        if(hit(25, 21, "up")) {healingWater(gp.dialogueState);}
        if(hit(26, 21, "up")) {healingWater(gp.dialogueState);}
        if(hit(25, 22, "down")) {healingWater(gp.dialogueState);}
        if(hit(26, 22, "down")) {healingWater(gp.dialogueState);}
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Shit, I fall into a pit!";
        gp.player.life -= 1;
    }
    public void teleport(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "What?!... \nWhere the hell am I?!";
        gp.player.worldX = gp.tileSize * 16;
        gp.player.worldY = gp.tileSize * 48;
    }
    public void healingWater(int gameState) {
        eventRect.x = 20;
        eventRect.y = 20;
        eventRect.width = 40;
        eventRect.height = 40;
        if(gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "I'm drinking the water. \nActually, the water have a strange taste. \nAnyway, I feel better.";
            gp.player.life += 2;
            if(gp.player.life > 6) {
                gp.player.life = 6;
            }
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
}
