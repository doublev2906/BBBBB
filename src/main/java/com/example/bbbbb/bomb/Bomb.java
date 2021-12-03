package com.example.bbbbb.bomb;

import com.example.bbbbb.*;
import com.example.bbbbb.sprite.MSprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {


    public boolean explode = false;

    private int countToExplode;
    private int intervalToExplode = 128;
    public boolean buffFlame = false;

    public Flame flame;
    public List<Flame> fl = new ArrayList<>();
    MSprite mSprite = new MSprite();
    Map mMap;
    Board board;
    public boolean stopFlame = false;

    public Bomb() {
        mSprite.initBombSprite();
    }

    public Bomb(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        mSprite.initBombSprite();
    }

    public void setmMap(Map mMap) {
        this.mMap = mMap;
    }

    @Override
    public void render(GraphicsContext gc) {

        int xa = (x + 8) / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
        int ya = (y + 8) / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
        if (fl.size() > 0) {
            fl.forEach(a -> a.render(gc));
        } else {
            gc.drawImage(mSprite.bombExplosion(), xa + 4, ya + 4, 24, 24);
        }
    }

    @Override
    public void update() {
        countToExplode++;
        if (countToExplode == intervalToExplode) {
            explode = true;
            initFlame();
        }
        if (explode) {
//            flame.update();
            fl.forEach(a -> a.update());
            fl.forEach(a -> {
                if (a.stop) stopFlame = true;
            });

        }


    }

    public void initFlame() {

        int mX = (x + 8) / Game.ENTITY_SIZE;
        int mY = (y + 8) / Game.ENTITY_SIZE;
        boolean block_left = false;
        boolean block_right = false;
        boolean block_up = false;
        boolean block_down = false;
        fl.add(new Flame(mX * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE));

        if (buffFlame){

            if (!board.entities[mY][mX + 1].collide(new Flame())){
                Flame flame = new Flame((mX + 1) * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE);
                flame.direction = Direction.CENTER_RIGHT;
                fl.add(flame);
            }
            else block_right = true;
            if (!board.entities[mY][mX - 1].collide(new Flame())) {
                Flame flame = new Flame((mX - 1) * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE);
                flame.direction = Direction.CENTER_LEFT;
                fl.add(flame);
            }
            else block_left = true;
            if (!board.entities[mY + 1][mX].collide(new Flame())) {
                Flame flame = new Flame(mX * Game.ENTITY_SIZE, (mY + 1) * Game.ENTITY_SIZE);
                flame.direction = Direction.CENTER_DOWN;
                fl.add(flame);
            } else block_down = true;
            if (!board.entities[mY - 1][mX].collide(new Flame())) {
                Flame flame = new Flame(mX * Game.ENTITY_SIZE, (mY - 1) * Game.ENTITY_SIZE);
                flame.direction = Direction.CENTER_UP;
                fl.add(flame);
            } else block_up = true;

            if (  !block_right && !board.entities[mY][mX + 2].collide(new Flame())){
                Flame flame = new Flame((mX + 2) * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE);
                flame.direction = Direction.RIGHT;
                fl.add(flame);
            }
            if (!block_left && !board.entities[mY][mX - 2].collide(new Flame()) ) {
                Flame flame = new Flame((mX - 2) * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE);
                flame.direction = Direction.LEFT;
                fl.add(flame);
            }
            if (!block_down &&  !board.entities[mY + 2][mX].collide(new Flame()) ) {
                Flame flame = new Flame(mX * Game.ENTITY_SIZE, (mY + 2) * Game.ENTITY_SIZE);
                flame.direction = Direction.DOWN;
                fl.add(flame);
            }
            if ( !block_up && !board.entities[mY - 2][mX].collide(new Flame()) ) {
                Flame flame = new Flame(mX * Game.ENTITY_SIZE, (mY - 2) * Game.ENTITY_SIZE);
                flame.direction = Direction.UP;
                fl.add(flame);
            }
        }
        else {
            if (!board.entities[mY][mX + 1].collide(new Flame())){
                Flame flame = new Flame((mX + 1) * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE);
                flame.direction = Direction.RIGHT;
                fl.add(flame);
            }
            if (!board.entities[mY][mX - 1].collide(new Flame())) {
                Flame flame = new Flame((mX - 1) * Game.ENTITY_SIZE, mY * Game.ENTITY_SIZE);
                flame.direction = Direction.LEFT;
                fl.add(flame);
            }
            if (!board.entities[mY + 1][mX].collide(new Flame())) {
                Flame flame = new Flame(mX * Game.ENTITY_SIZE, (mY + 1) * Game.ENTITY_SIZE);
                flame.direction = Direction.DOWN;
                fl.add(flame);
            }
            if (!board.entities[mY - 1][mX].collide(new Flame())) {
                Flame flame = new Flame(mX * Game.ENTITY_SIZE, (mY - 1) * Game.ENTITY_SIZE);
                flame.direction = Direction.UP;
                fl.add(flame);
            }
        }
//
    }

    public Flame flameAt(int x, int y) {
        if (!explode) return null;
        for (Flame f : fl) {
            if (f.getX() == x && f.getY() == y) {
                return f;
            }
        }
        return null;
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

}
