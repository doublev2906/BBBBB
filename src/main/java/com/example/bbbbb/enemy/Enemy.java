package com.example.bbbbb.enemy;

import com.example.bbbbb.Board;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Game;
import com.example.bbbbb.bomb.Bomb;

public abstract class Enemy extends Entity {

    boolean left,right,up,down;
    boolean isMoving = false;
    Board board;
    protected boolean alive = true;
    int toX;
    int toY;
    int speed = 1;


    public boolean canMove(int nextX, int nextY) {
        int nextX_1 = (int) (nextX / Game.ENTITY_SIZE);
        int nextY_1 = (int) (nextY / Game.ENTITY_SIZE);

        int nextX_2 = (int) (nextX + Game.ENTITY_SIZE -1) / Game.ENTITY_SIZE;
        int nextY_2 = (int) (nextY / Game.ENTITY_SIZE);

        int nextX_3 = (int) (nextX / Game.ENTITY_SIZE);
        int nextY_3 = (int) (nextY + Game.ENTITY_SIZE - 1) / Game.ENTITY_SIZE;

        int nextX_4 = (int) (nextX + Game.ENTITY_SIZE - 1) / Game.ENTITY_SIZE;
        int nextY_4 = (int) (nextY + Game.ENTITY_SIZE - 1) / Game.ENTITY_SIZE;



        return !(board.entities[nextY_1][nextX_1].collide(this) ||
                board.entities[nextY_2][nextX_2].collide(this) ||
                board.entities[nextY_3][nextX_3].collide(this) ||
                board.entities[nextY_4][nextX_4].collide(this));
    }







    public int randomInt(int min, int max) {

        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void isKill(){
        Bomb bomb = board.getBomb();
        if (bomb != null){
            if (bomb.fl.size() > 0){
                int mx = x / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                int my = y / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                bomb.fl.forEach(a -> {
                    if (a.getX() == mx && a.getY() == my){
                        alive = false;
                    }
                });
            }
        }
        Bomb bomb1 = board.getBomb1();
        if (bomb1 != null){
            if (bomb1.fl.size() > 0){
                int mx = x / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                int my = y / Game.ENTITY_SIZE * Game.ENTITY_SIZE;
                bomb1.fl.forEach(a -> {
                    if (a.getX() == mx && a.getY() == my){
                        alive = false;
                    }
                });
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }

}
