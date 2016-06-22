package de.hn.vhauryn.rtype.entities;

import de.hn.vhauryn.rtype.ui.R;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;

public class GameEntity {

    protected Image img = null;

    public Rectangle getBoundaries() {
        return boundaries;
    }

    protected Rectangle boundaries;
    protected int health;

    public GameEntity(Rectangle boundaries,String image) {
        img = R.getInstance().getImage(image).getImage();
        this.boundaries = boundaries;
    }


    public void move(int x,int y){
        boundaries.x+=x;
        boundaries.y+=y;
    }

    public void render(Graphics2D g, ImageObserver i){
        g.drawImage(img, boundaries.x, boundaries.y, i);
        g.setColor(Color.RED);
        g.drawRect(boundaries.x,boundaries.y, boundaries.width,boundaries.height);
    }
    public void simulate(){}
}
