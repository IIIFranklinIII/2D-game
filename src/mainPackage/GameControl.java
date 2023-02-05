package mainPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl implements KeyListener {
    GameConfig gp;
    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;

    public GameControl(GameConfig gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.titleState) {
            if(gp.ui.titleScreenState == 0) {
                if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
             else if(gp.ui.titleScreenState == 1) {
                if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        System.out.println("treasure");
                        gp.gameState = gp.introState;
                    }
                    if(gp.ui.commandNum == 1) {
                        System.out.println("sheriff");
                        gp.gameState = gp.introState;
                     }
                    if(gp.ui.commandNum == 2) {
                        System.out.println("developer");
                        gp.gameState = gp.introState;
                    }
                    if(gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
             else if(gp.gameState == gp.titleState) {
                 if(code == KeyEvent.VK_ENTER);
                 enterPressed = true;
            }
        }
        if(gp.gameState == gp.introState) {
            if(gp.ui.introScreenState == 0) {
                if(code == KeyEvent.VK_ENTER) {
                    gp.ui.introScreenState = 1;
                }
            }
            else if(gp.ui.introScreenState == 1) {
                if(code == KeyEvent.VK_ENTER) {
                    gp.ui.introScreenState = 2;
                }
            }
            else if(gp.ui.introScreenState == 2) {
                if(code == KeyEvent.VK_ENTER) {
                    gp.ui.introScreenState = 3;
                }
            }
            else if(gp.ui.introScreenState == 3) {
                if(code == KeyEvent.VK_ENTER) {
                    gp.ui.introScreenState = 4;
                }
            }else if(gp.ui.introScreenState == 4) {
                if(code == KeyEvent.VK_ENTER) {
                    gp.ui.introScreenState = 5;
                }
            }
            else if(gp.ui.introScreenState == 5) {
                if(code == KeyEvent.VK_ENTER) {
                    gp.ui.introScreenState = 6;
                }
            }
            else if(gp.ui.introScreenState == 6) {
                gp.ui.introScreenState = 7;
                gp.gameState = gp.playState;
            }
            else if(gp.ui.introScreenState == 7) {
                gp.gameState = gp.playState;
            }
            else if(gp.gameState == gp.introState) {
                if(code == KeyEvent.VK_ENTER);
                enterPressed = true;
            }
        }

        if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
    }
}
