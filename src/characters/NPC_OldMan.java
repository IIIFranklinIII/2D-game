package characters;

import mainPackage.GameConfig;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GameConfig gp) {
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("npc/oldman/oldmanUp");
        up2 = setup("npc/oldman/oldmanUp");
        down1 = setup("npc/oldman/oldmanDown");
        down2 = setup("npc/oldman/oldmanDown");
        left1 = setup("npc/oldman/oldmanLeft1");
        left2 = setup("npc/oldman/oldmanLeft2");
        right1 = setup("npc/oldman/oldmanRight1");
        right2 = setup("npc/oldman/oldmanRight2");
    }

    public void setDialogue() {
        dialogue[0] = "Oh, an another treasure hunter...";
        dialogue[1] = "Your friends just ran through here...";
        dialogue[2] = "Robbed my house... \nBroke the walls...";
        dialogue[3] = "Here... Take my last coin.";
        dialogue[4] = "What? \nKeep for myself?";
        dialogue[5] = "Thank you, my boy.";
        dialogue[6] = "So, uh, your friends ran by this way, \nyou have to go. Good luck";
        dialogue[7] = "My name is Chika-chika Slim Shady.";
        dialogue[8] = "Excuse me. \nCan I have the attention of the class.";
        dialogue[9] = "For one second";
        dialogue[10] = "Hi kids! Do you like violence?";
        dialogue[11] = "Wanna see me stick Nine inch Nails, \nthrough each one of my eyelids?";
        dialogue[12] = "Wanna copy me \nand do exactly like I did?";
        dialogue[13] = "Try acid and get fucked up worse \nthat my life is?";
        dialogue[14] = "My brain's dead weight, \nI'm tryin' to get my head straight.";
        dialogue[15] = "But I can't figure out \nwhich Spice Girl I want to impregnate.";
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
            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void speak() {
        super.speak();
    }
}
