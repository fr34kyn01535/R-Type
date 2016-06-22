package de.hn.vhauryn.rtype.entities;

import de.hn.vhauryn.rtype.ui.R;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class Boss extends ShootingEntity {
    public Boss() {
        super(new Rectangle(460, 40, 500, 550), "boss.gif", false);
        health = 5;
    }


    @Override
    public void render(Graphics2D g, ImageObserver i){
        super.render(g,i);

    }

    @Override
    public void dealDamage(int damage) {
        super.dealDamage(damage);
    }
}