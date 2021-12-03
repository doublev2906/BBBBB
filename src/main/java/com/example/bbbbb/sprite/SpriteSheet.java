package com.example.bbbbb.sprite;


import com.example.bbbbb.Bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    private String _path;
    public final int SIZE;
    public int[] _pixels;
    public BufferedImage image;
    public static SpriteSheet tiles = new SpriteSheet("classic.png", 256);
    public static SpriteSheet flames = new SpriteSheet("flame.png", 160);

    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            URL a = Bomberman.class.getResource(_path);
            image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
