package com.example.bbbbb.tiles;

import com.example.bbbbb.Bomberman;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block extends Tile {



    public Block(int x, int y) {
        super(x,y);
        image = new Image(String.valueOf(Bomberman.class.getResource("solidblock.png")));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image,x,y, Game.ENTITY_SIZE,Game.ENTITY_SIZE);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
