package de.hn.vhauryn.rtype.entities;

import de.hn.vhauryn.rtype.logic.Game;
import de.hn.vhauryn.rtype.ui.R;

import java.awt.*;
import java.awt.image.ImageObserver;

public class LivingEntity extends GameEntity {
    protected int health = 5;

    public boolean isDead() {
        return dead;
    }

    protected boolean dead = false;
    private boolean exploding = false;
    private Image explosionImage = null;

    public LivingEntity(Rectangle boundaries, String image) {
        super(boundaries,image);
        explosionImage = R.getInstance().getImage("boom.gif").getImage();
    }

    public void dealDamage(int damage){
        health -=damage;
    }

    @Override
    public void render(Graphics2D g, ImageObserver i){
        if(!dead){
            super.render(g,i);

            if(exploding){

                int x = boundaries.x + boundaries.width / 2 - explosionImage.getWidth(i)/2;
                int y= boundaries.y + boundaries.height / 2 - explosionImage.getHeight(i)/2;

                g.drawImage(explosionImage, x,y,i);
            }
        }
    }

    @Override
    public void simulate() {
        super.simulate();

        if(dead){
            die();
        }

        if(health <= 0 && !exploding){
            health = 0;
            exploding = true;
            new javax.swing.Timer(3000, actionEvent -> {exploding = false; dead = true;}).start();
            R.getInstance().playSound("boom.wav");
        }
    }

    public void die(){
        //
    }
}
