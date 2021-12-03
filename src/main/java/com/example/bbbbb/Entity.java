package com.example.bbbbb;

import com.example.bbbbb.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Sprite sprite;
    protected Image image;
    protected boolean isRemove = false;

    public abstract void render(GraphicsContext gc);
    public abstract void update();
    public abstract boolean collide(Entity e);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
