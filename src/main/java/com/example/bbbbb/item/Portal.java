package com.example.bbbbb.item;

import com.example.bbbbb.Board;
import com.example.bbbbb.Bomber;
import com.example.bbbbb.Entity;
import com.example.bbbbb.sprite.Sprite;

public class Portal extends Item{
    public Portal(int x, int y) {
        this.x = x;
        this.y = y;
        image = Sprite.portal.getFxImage();
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber){
            Board b = ((Bomber) e).getBoard();
            if (b.enemies.size() == 0){
                ((Bomber) e).setWinGame();
            }

        }
        return false;
    }
}
