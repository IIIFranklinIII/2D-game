import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Sprite implements Runnable{
    private String sprite = "https://github.com/IIIFranklinIII/2D-game/blob/main/src/content/carrot.png?raw=true";

    private int dx;
    private int dy;
    private int x;
    private int y;
    private boolean r = false;
    private Image image;

    public Sprite() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(sprite));
        image = ii.getImage();
        x = 40;
        y = 60;
    }

    public void run() {
        Sprite s1 = new Sprite();
        new Thread(s1).start();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE){
            y-=120;
        }
        if (key == KeyEvent.VK_SHIFT) {
            r = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
            if (r == true){dx = -5;}
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
            if (r == true){dx = 5;}
        }
        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE){
            y+=120;
        }
        if (key == KeyEvent.VK_SHIFT) {
            r = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
