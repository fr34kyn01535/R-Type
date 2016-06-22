package de.hn.vhauryn.rtype.logic;

import de.hn.vhauryn.rtype.entities.*;
import de.hn.vhauryn.rtype.ui.R;
import de.hn.vhauryn.rtype.windows.GameWindow;

import java.util.*;
import java.awt.*;
import javax.swing.*;


public class Game extends JComponent {

    private static boolean gameOver = false;

    private static Player player = null;
    public static Player getPlayer(){return player;}
    private static Boss boss = null;

    private static ArrayList<LivingEntity> livingEntities = new ArrayList<LivingEntity>();

    public static LivingEntity checkColliding(Rectangle boundaries){
        synchronized(livingEntities){
            for (LivingEntity ce :livingEntities) {
                Rectangle entityBoundaries = ce.getBoundaries();
                if(entityBoundaries.x < boundaries.x + boundaries.width && entityBoundaries.x + entityBoundaries.width > boundaries.x && entityBoundaries.y < boundaries.y + boundaries.height && entityBoundaries.y + entityBoundaries.height > boundaries.y)
                   return ce;
            }
        }
        return null;
    }

    private static EnumMap<Movement, Boolean> movement = new EnumMap<Movement,Boolean>(Movement.class);
    public static void putMovement(Movement key,Boolean value){ movement.put(key,value);}
    public static boolean getMovement(Movement key){ return movement.get(key);}
    private static EnumMap<Shooting, Boolean> shooting = new EnumMap<Shooting,Boolean>(Shooting.class);
    public static void putShooting(Shooting key,Boolean value){ shooting.put(key,value);}
    public static boolean getShooting(Shooting key){ return shooting.get(key);}

    public enum Movement{ UP,DOWN,LEFT,RIGHT }
    public enum Shooting{ SHOT1,SHOT2 }


    private Graphics2D g = null;

    public static int boundaryX = 950;
    public static int boundaryY = 550;

    private static GameWindow context;

    public Game(GameWindow context) {
        super();

        this.context = context;
        R.getInstance().getSound("bgm.wav").play(-20.0f,true);

        movement.put(Movement.UP,false);
        movement.put(Movement.DOWN,false);
        movement.put(Movement.LEFT,false);
        movement.put(Movement.RIGHT,false);

        shooting.put(Shooting.SHOT1,false);
        shooting.put(Shooting.SHOT2,false);

        player = new Player();
        livingEntities.add(player);
        boss = new Boss();
        livingEntities.add(boss);


    }

    private void renderAsset(String name, int posX,int posY){
        g.drawImage(R.getInstance().getImage(name).getImage(),posX,posY,this);
    }

    public static void over(){
        gameOver = true;
    }

    @Override
    public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            g = (Graphics2D)graphics;

            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);



            if(gameOver){
                g.setColor(Color.RED);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 125));
                g.drawString("GAME OVER",110,315);
                return;
            }

            renderAsset("bg.png",0,0);
            renderAsset("sun.gif",800, 30);
            renderAsset("nova.gif", 540, 530);

            Iterator<LivingEntity> i = livingEntities.iterator();
            while (i.hasNext()) {
                LivingEntity e = i.next();

                e.render(g,this);
                e.simulate();

                if(e.isDead()) {
                    i.remove();
                }
            }

            g.dispose();

    }
}

