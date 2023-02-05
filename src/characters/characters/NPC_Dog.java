package characters;

import mainPackage.GameConfig;

import java.util.Random;

public class NPC_Dog extends Entity{
    public NPC_Dog(GameConfig gp) {
        super(gp);

        direction = "down";
        speed = 2;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("npc/dog/dogLeft1");
        up2 = setup("npc/dog/dogLeft2");
        down1 = setup("npc/dog/dogRight1");
        down2 = setup("npc/dog/dogRight2");
        left1 = setup("npc/dog/dogLeft1");
        left2 = setup("npc/dog/dogLeft1");
        right1 = setup("npc/dog/dogRight1");
        right2 = setup("npc/dog/dogRight2");
    }

    public void setDialogue() {
        dialogue[0] = "OOH, YEAH, \nBABY PET ME!!!!";
    }

    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 23) {
                direction = "up";
            }
            if(i > 23 && i <= 55) {
                direction = "down";
            }
            if(i > 55 && i <= 75) {
                direction = "left";
            }
            if(i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
