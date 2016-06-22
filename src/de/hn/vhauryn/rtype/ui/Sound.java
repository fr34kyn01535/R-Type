package de.hn.vhauryn.rtype.ui;

import javax.sound.sampled.*;
import java.io.*;

public class Sound {

    protected File file = null;
    private boolean isRunning = false;


    public Sound(File f){
        this.file = f;
    }

    public void play(){
        play(0,false);
    }

    public void play(float volume){
        play(volume,false);
    }

    public void play(float volume,boolean loop) {
        if(isRunning) return;
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            if (loop){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }else {
                clip.loop(0);
                javax.swing.Timer t = new javax.swing.Timer((int) (clip.getMicrosecondLength() / 1000), actionEvent -> {
                    isRunning = false;
                });
            }
            clip.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
