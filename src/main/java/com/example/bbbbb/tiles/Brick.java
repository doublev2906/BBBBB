package com.example.bbbbb.tiles;

import com.example.bbbbb.Bomberman;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Game;
import com.example.bbbbb.bomb.Flame;
import com.example.bbbbb.enemy.SkullHead;
import com.example.bbbbb.sprite.MSprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends Tile {

    public boolean isDestroy = false;
    MSprite mSprite = new MSprite();

    public Brick(int x, int y) {
        super(x, y);
        image = new Image(String.valueOf(Bomberman.class.getResource("wall.png")));
        mSprite.initBrickSprite();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDestroy) {
            gc.drawImage(mSprite.brickFade(), x, y, Game.ENTITY_SIZE, Game.ENTITY_SIZE);
        } else {
            gc.drawImage(image, x, y, Game.ENTITY_SIZE, Game.ENTITY_SIZE);
        }

    }

    @Override
    public void update() {
        if (isDestroy) {
            if (mSprite.end) {
                isRemove = true;
            }
        }

    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            isDestroy = true;
        }
        if (e instanceof SkullHead) return false;
        return true;
    }
}
