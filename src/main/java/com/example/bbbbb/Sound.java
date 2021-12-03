package com.example.bbbbb;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    public static void playSound(String fileName) {


        try {
            String path = (System.getProperty("user.dir") + "\\src\\sound\\" + fileName + ".wav");
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loopSound(String fileName) {
        String path = (System.getProperty("user.dir") + "\\src\\sound\\" + fileName + ".wav");

        try {
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playerDead() {
        playSound("endgame3");
    }

    public static void getItem() {
        playSound("beepSmall");
    }


    public static void ThemeSound() {

        loopSound("burningMemory");
    }

}
