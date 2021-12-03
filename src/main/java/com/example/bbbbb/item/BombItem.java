package com.example.bbbbb.item;

import com.example.bbbbb.Bomber;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Sound;
import com.example.bbbbb.sprite.Sprite;

public class BombItem extends Item{

    public BombItem(int x, int y) {
        this.x = x;
        this.y = y;
        image = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public boolean collide(Entity e) {
        if  (e instanceof Bomber){
            ((Bomber) e).setBuffBomb();
            isRemove = true;
            Sound.getItem();
        }
        return false;
    }
}
