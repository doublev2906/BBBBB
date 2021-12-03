package com.example.bbbbb.item;

import com.example.bbbbb.Entity;
import com.example.bbbbb.Game;
import javafx.scene.canvas.GraphicsContext;

public class Item extends Entity {

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image,x,y, Game.ENTITY_SIZE,Game.ENTITY_SIZE);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
