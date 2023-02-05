package object;

import mainPackage.GameConfig;
import mainPackage.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectManager {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    Tools uTool = new Tools();

    public void draw(Graphics2D g2, GameConfig gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize, null);
        }
    }
}
