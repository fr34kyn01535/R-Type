package de.hn.vhauryn.rtype.windows;

import de.hn.vhauryn.rtype.logic.*;
import de.hn.vhauryn.rtype.ui.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameWindow extends JFrame {

    public static GameWindow Instance = null;

    public GameWindow() {
        Instance = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(960, 640);
        setLocationRelativeTo(null);
        setIconImage(R.getInstance().getImage("r-type-icon.png").getImage());

        add(new Game(this));

        bindKeyAdapter();

        setVisible(true);
    }

    private void bindKeyAdapter() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (KeyEvent.VK_ESCAPE == e.getKeyCode())
                    System.exit(0);

                if (!Game.getPlayer().isDead())
                    switch(e.getKeyCode()){
                        case(KeyEvent.VK_D):
                            Game.putMovement(Game.Movement.RIGHT,true);
                            break;
                        case (KeyEvent.VK_A):
                            Game.putMovement(Game.Movement.LEFT,true);
                            break;
                        case (KeyEvent.VK_W):
                            Game.putMovement(Game.Movement.UP,true);
                            break;
                        case (KeyEvent.VK_S):
                            Game.putMovement(Game.Movement.DOWN,true);
                            break;
                        case KeyEvent.VK_SPACE:
                            Game.putShooting(Game.Shooting.SHOT1,true);
                            break;
                        case (KeyEvent.VK_Q):
                            Game.putShooting(Game.Shooting.SHOT2,true);
                            break;
                    }
            }
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                switch(e.getKeyCode()){
                    case(KeyEvent.VK_D ):
                        Game.putMovement(Game.Movement.RIGHT,false);
                        break;
                    case(KeyEvent.VK_A):
                        Game.putMovement(Game.Movement.LEFT,false);
                        break;
                    case(KeyEvent.VK_W):
                        Game.putMovement(Game.Movement.UP,false);
                        break;
                    case(KeyEvent.VK_S):
                        Game.putMovement(Game.Movement.DOWN,false);
                        break;
                    case(KeyEvent.VK_SPACE):
                        Game.putShooting(Game.Shooting.SHOT1,false);
                        break;
                    case(KeyEvent.VK_Q):
                        Game.putShooting(Game.Shooting.SHOT2,false);
                        break;
                }
            }
        });
    }
}
