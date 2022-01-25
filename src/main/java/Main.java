import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {


        TerminalSize ts = new TerminalSize(120, 30);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(ts);
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);


        Random random = new Random();
        boolean continueReadingInput = true;
        boolean gameOver = false;
        final char BLOCK = '\u2588';
        Character c = ' ';
        Player P = new Player();
        Monster M = new Monster();
        int xPP = P.getPlayerPosX();
        int yPP = P.getPlayerPosY();
        int xMP = random.nextInt(xPP + 20, xPP + 100);
        int yMP = random.nextInt(yPP - 20, yPP + 10);
        terminal.setCursorPosition(xPP, yPP);
        terminal.putCharacter(P.getPlayer());
        terminal.setCursorPosition(xMP, yMP);
        terminal.putCharacter(M.getMonster());


        List<Walls> barriers = new ArrayList<>();
        for (int i = 0; i <= 120; i++) {
            barriers.add(new Walls(i, 0));
        }
        for (int i = 0; i <= 30; i++) {
            barriers.add(new Walls(0, i));
        }
        for (int i = 0; i <= 120; i++) {
            barriers.add(new Walls(i, 30));
        }
        for (int i = 0; i <= 30; i++) {
            barriers.add(new Walls(120, i));
        }
        for (Walls block : barriers) {
            terminal.setCursorPosition(block.getWallPosX(), block.getWallPosY());
            terminal.putCharacter(BLOCK);
        }


        while (continueReadingInput) {

            KeyStroke keyStroke = null;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);


            if (keyStroke != null) {
                c = keyStroke.getCharacter();
                int oldPPosX = xPP;
                int oldPPosY = yPP;
                int oldPosMX = xMP;
                int oldPosMY = yMP;

                switch (keyStroke.getKeyType()) {
                    case ArrowDown:
                        yPP = yPP + 1;
                        if (yPP < yMP) {
                            yMP = yMP - 1;
                        } else if (yPP == yMP) {
                            yMP = yMP;
                        } else {
                            yMP = yMP + 1;
                        }
                        break;
                    case ArrowUp:
                        yPP = yPP - 1;
                        if (yPP > yMP) {
                            yMP = yMP + 1;
                        } else if (yPP == yMP) {
                            yMP = yMP;
                        } else {
                            yMP = yMP - 1;
                        }
                        break;
                    case ArrowRight:
                        xPP = xPP + 1;
                        if (xPP < xMP) {
                            xMP = xMP - 1;
                        } else if (xPP == xMP) {
                            xMP = xMP;
                        } else {
                            xMP = xMP + 1;
                        }

                        break;
                    case ArrowLeft:
                        xPP = xPP - 1;
                        if (xPP > xMP) {
                            xMP = xMP + 1;
                        } else if (xPP == xMP) {
                            xMP = xMP;
                        } else {
                            xMP = xMP - 1;
                        }
                        break;
                }

                if (xPP == xMP && yPP == yMP) {
                    M.kill();
                    terminal.putCharacter('\u2620');
                    Thread.sleep(500);
                    gameOver = true;
                }

                terminal.setCursorPosition(oldPPosX, oldPPosY);
                terminal.putCharacter(' ');
                terminal.setCursorPosition(oldPosMX, oldPosMY);
                terminal.putCharacter(' ');
                terminal.setCursorPosition(xPP, yPP);
                terminal.putCharacter(P.getPlayer());
                terminal.setCursorPosition(xMP, yMP);
                terminal.putCharacter(M.getMonster());
            }
            terminal.flush();

            if (gameOver) {
                terminal.close();
                break;
            }
        }

    }

}

