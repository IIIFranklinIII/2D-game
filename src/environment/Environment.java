package environment;

import mainPackage.GameConfig;

import java.awt.*;

public class Environment {
    GameConfig gp;
    Lighting lighting;

    public Environment(GameConfig gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp,450);
    }
    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
}
