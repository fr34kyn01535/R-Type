package de.hn.vhauryn.rtype.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

public class R {

    private static R instance = null;
    public static R getInstance(){
        if (instance == null) instance = new R();
        return instance;
    }

    private HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    public Sound getSound(String sound){
        return sounds.get(sound);
    };

    public void playSound(String sound){
        getSound(sound).play();
    }

    private HashMap<String, ImageIcon> images = new HashMap<String, ImageIcon>();
    public ImageIcon getImage(String image){
        return images.get(image);
    };

    private void loadImageIcon(String file){
        images.put(file,new ImageIcon(getClass().getResource("/images/" +file)));
    };

    private void loadSound(String file){
        sounds.put(file,new Sound(new File(getClass().getResource("/audio/" +file).getPath())));
    };

    public R(){
        ;

        try {
            for(final File file : new File(R.class.getResource("/audio/").toURI()).listFiles()) {
                loadSound(file.getName());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            for(final File file : new File(R.class.getResource("/images/").toURI()).listFiles()) {
                loadImageIcon(file.getName());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
