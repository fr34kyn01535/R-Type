package de.hn.vhauryn.rtype.entities;


import de.hn.vhauryn.rtype.logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;
import java.util.Timer;

public class ShootingEntity extends LivingEntity {
    private ArrayList<ProjectileEntity> projectiles = new ArrayList<ProjectileEntity>();
    private boolean shootingDirection = false;

    public ShootingEntity(Rectangle boundaries,String image, boolean shootingDirection) {
        super(boundaries,image);
        this.shootingDirection = shootingDirection;
    }

    public void shoot(Rectangle boundaries,String image,int speedX,int speedY,int damage){
        if(!shootingDirection) {
            speedX = -speedX;
        }
        ProjectileEntity projectile = new ProjectileEntity(boundaries,image,speedX,speedY,damage);

        projectiles.add(projectile);

        javax.swing.Timer t = new javax.swing.Timer(10000, actionEvent -> projectiles.remove(projectile));
        t.setRepeats(false);
        t.start();
    }

    @Override
    public void render(Graphics2D g, ImageObserver i){
        super.render(g,i);

        Iterator<ProjectileEntity> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            ProjectileEntity e = iterator.next();
            e.render(g,i);
        }
    }


    @Override
    public void simulate(){
        super.simulate();

        Iterator<ProjectileEntity> i = projectiles.iterator();
        while (i.hasNext()) {
            ProjectileEntity e = i.next();
            e.simulate();
            if(e.boundaries.x > Game.boundaryX || e.dead) i.remove();
        }
    }
}
