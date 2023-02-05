package preset;

import mainPackage.GameConfig;
import mainPackage.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GameConfig gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GameConfig gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/gameResources/maps/world.txt");
    }

    public void getTileImage() {
        setup(0,"grass", false);
        setup(1,"bricks", true);
        setup(2,"water", true);
        setup(3,"dirt", false);
        setup(4,"tree", true);
        setup(5,"sand", false);
        setup(6,"water-grass", false);
        setup(7,"boat", true);
        setup(8,"wood", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        Tools uTool = new Tools();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/gameResources/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(gp.player.screenX > gp.player.worldX) {
                screenX = worldX;
            }
            if(gp.player.screenY > gp.player.worldY) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            } else if(gp.player.screenX > gp.player.worldX ||
                      gp.player.screenY > gp.player.worldY ||
                      rightOffset > gp.worldWidth - gp.player.worldX ||
                      bottomOffset > gp.worldHeight - gp.player.worldY) {
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }

            worldCol++;

            if(worldCol == 50) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
