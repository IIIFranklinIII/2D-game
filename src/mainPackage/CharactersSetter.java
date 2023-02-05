package mainPackage;

import characters.NPC_Dog;
import characters.NPC_OldMan;

public class CharactersSetter {
    GameConfig gp;

    public CharactersSetter(GameConfig gp) {
        this.gp = gp;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

        gp.npc[1] = new NPC_Dog(gp);
        gp.npc[1].worldX = gp.tileSize * 30;
        gp.npc[1].worldY = gp.tileSize * 21;
    }
}
