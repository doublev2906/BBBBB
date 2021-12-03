package com.example.bbbbb.bomb;

import com.example.bbbbb.Direction;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Map;
import com.example.bbbbb.sprite.MSprite;
import javafx.scene.canvas.GraphicsContext;

public class Flame extends Entity {
    public boolean stop = false;
    public MSprite mSprite = new MSprite();
    public Map mMap;
    public Direction direction;

    public Flame() {
    }

    public Flame(int x, int y) {
        this.x = x;
        this.y = y;
        mSprite.initFlameAnim();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (direction == Direction.DOWN) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimDown), x, y);
        } else if (direction == Direction.UP) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimUp), x, y);
        } else if (direction == Direction.LEFT) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimLeft), x, y);
        } else if (direction == Direction.RIGHT) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimRight), x, y);
        } else if (direction == Direction.CENTER_DOWN) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimCenterDown), x, y);
        } else if (direction == Direction.CENTER_UP) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimCenterUp), x, y);
        } else if (direction == Direction.CENTER_LEFT) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimCenterLeft), x, y);
        } else if (direction == Direction.CENTER_RIGHT) {
            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimCenterRight), x, y);
        } else gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimCenter), x, y);

//        gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimCenter),x,y);
//        int mX = x/32;
//        int mY = y/32;
//
//        if (mMap.mapTile[mY][mX+1] instanceof Grass){
//            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimRight),(mX + 1)*32,y);
//        }
//
//        if (mMap.mapTile[mY][mX-1] instanceof Grass){
//            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimLeft),(mX-1)*32,y);
//        }
//
//        if (mMap.mapTile[mY-1][mX] instanceof Grass){
//            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimUp),x,(mY-1)*32);
//        }
//
//        if (mMap.mapTile[mY+1][mX] instanceof Grass){
//            gc.drawImage(mSprite.flameExplosion(mSprite.flameAnimDown),x,(mY+1)*32);
//        }


    }

    @Override
    public void update() {
        if (mSprite.flameStop) {
            stop = true;
        }
//        int mX = x/32;
//        int mY = y/32;
//        if (mMap != null){
//            if (mMap.mapTile[mY][mX+1] instanceof Brick){
//                mMap.mapTile[mY][mX+1].collide(this);
//            }
//
//            if (mMap.mapTile[mY][mX-1] instanceof Brick){
//                mMap.mapTile[mY][mX-1].collide(this);
//            }
//
//            if (mMap.mapTile[mY-1][mX] instanceof Brick){
//                mMap.mapTile[mY-1][mX].collide(this);
//            }
//
//            if (mMap.mapTile[mY+1][mX] instanceof Brick){
//                mMap.mapTile[mY+1][mX].collide(this);
//            }
//
//        }

    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
