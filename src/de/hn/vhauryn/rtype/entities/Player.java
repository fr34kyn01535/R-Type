package de.hn.vhauryn.rtype.entities;

import de.hn.vhauryn.rtype.*;
import de.hn.vhauryn.rtype.logic.Game;
import de.hn.vhauryn.rtype.ui.R;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends ShootingEntity {
    private boolean engine = false;
    private Image engineImage = null;
    private Image explosionImage = null;
    private Image liveImage = null;
    private Image noLiveImage = null;

    private int speedX = 1;
    private int speedY = 1;

    private long shot1Cooldown = 50;
    private long shot1LastUsed = 0;
    private long shot2Cooldown = 50;
    private long shot2LastUsed = 0;


    public Player(){
        super(new Rectangle(286,50,50,70),"rtype.gif",true);
        engineImage = R.getInstance().getImage("mov.gif").getImage();
        explosionImage = R.getInstance().getImage("boom.gif").getImage();
        liveImage = R.getInstance().getImage("1up.gif").getImage();
        noLiveImage = R.getInstance().getImage("1up_lost.gif").getImage();
    }

    private void shootFireball(String image,int width,int height,int damage){
        shoot(new Rectangle(boundaries.x+boundaries.width+5,boundaries.y+boundaries.height/2-height/2,width,height),image+".gif",1,0,damage);
        R.getInstance().playSound(image+".wav");
    }

    @Override
    public void render(Graphics2D g, ImageObserver i){
        if(!dead) {

            engine = false;

            if(Game.getMovement(Game.Movement.DOWN)) move(0,speedY);
            if(Game.getMovement(Game.Movement.UP)) move(0,-speedY);
            if(Game.getMovement(Game.Movement.LEFT)) move(-speedX,0);
            if(Game.getMovement(Game.Movement.RIGHT)) move(speedX,0);


            if(Game.getShooting(Game.Shooting.SHOT1) && (System.nanoTime() - shot1LastUsed)/10001000 > shot1Cooldown) {
                shot1LastUsed = System.nanoTime();
                shootFireball("shot1",50,20,1);
                health-=1;
            }

            if(Game.getShooting(Game.Shooting.SHOT2)&& (System.nanoTime() - shot2LastUsed)/10001000 > shot2Cooldown) {
                shot2LastUsed = System.nanoTime();
                shootFireball("shot2",72,32,5);
            }

            if (engine)
                g.drawImage(engineImage, boundaries.x-6, boundaries.y+27, i);

            for(int j = 0;j < 5;j++){
                if(j<health)
                    g.drawImage(liveImage, 50+(j*40), 550, i);
                else
                    g.drawImage(noLiveImage, 50+(j*40), 550, i);
            }

            super.render(g, i);

        }
    }

    @Override
    public void move(int x, int y) {
        if((boundaries.x + x) < 0) return;
        if((boundaries.y + y) < 0) return;
        if((boundaries.x + x) > Game.boundaryX) return;
        if((boundaries.y + y) > Game.boundaryY) return;

        engine = true;
        super.move(x, y);
    }

    @Override
    public void die(){
        Game.over();
    }
}
