package de.hn.vhauryn.rtype.entities;

import de.hn.vhauryn.rtype.logic.Game;
import de.hn.vhauryn.rtype.ui.R;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ProjectileEntity extends LivingEntity {
    private int damage;
    public int getDamage(){ return damage; }

    private int speedX = 0;
    private int speedY = 0;

    public ProjectileEntity(Rectangle boundaries, String image, int speedX,int speedY,int damage) {
        super(boundaries,image);
        this.damage = damage;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public void simulate() {
        super.simulate();

        LivingEntity entity = Game.checkColliding(boundaries);
        if(entity != null){
            entity.dealDamage(damage);
            dead = true;
        }

        move(speedX,speedY);
    }
}
