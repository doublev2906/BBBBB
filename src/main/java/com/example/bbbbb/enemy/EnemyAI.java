package com.example.bbbbb.enemy;

import com.example.bbbbb.Bomber;
import com.example.bbbbb.Direction;

import java.util.Random;

public class EnemyAI {
//    Bomber _bomber;
//    Enemy _e;
//    Random random;
//
//    public EnemyAI(Bomber bomber, Enemy e) {
//        _bomber = bomber;
//        _e = e;
//        random = new Random();
//    }


//    public int calculateDirection() {
//        // TODO: cài đặt thuật toán tìm đường đi
//        if(_bomber == null)
//            return random.nextInt(4);
//
//        int vertical = random.nextInt(2);
//
//        if(vertical == 1) {
//            int v = calculateRowDirection();
//            if(v != -1)
//                return v;
//            else
//                return calculateColDirection();
//
//        } else {
//            int h = calculateColDirection();
//
//            if(h != -1)
//                return h;
//            else
//                return calculateRowDirection();
//        }
//    }
//    protected int calculateColDirection() {
//        if(_bomber.getX() / 32 < _e.getX()/32)
//            return 3;
//        else if(_bomber.getX() / 32 > _e.getX()/32)
//            return 1;
//
//        return -1;
//    }
//
//    protected int calculateRowDirection() {
//        if(_bomber.getY() < _e.getY())
//            return 0;
//        else if(_bomber.getY() > _e.getY())
//            return 2;
//        return -1;
//    }
    Bomber player;
    Enemy enemy;

    public EnemyAI(Bomber player, Enemy enemy) {
        this.enemy = enemy;
        this.player = player;
    }

    public Direction diNgang() {

        if (enemy.getX() > player.getX()) {
            return Direction.LEFT;
        } else if (enemy.getX() == player.getX()) {
            return diDoc();
        } else {
            return Direction.RIGHT;
        }
    }

    public Direction diDoc() {
        if (enemy.getX() == player.getX()
                && enemy.getY() == player.getY()) return Direction.STATIONARY;
        else if (enemy.getY() > player.getY()) {
            return Direction.UP;
        } else if (enemy.getY()== player.getY()) {
            return diNgang();
        } else {
            return Direction.DOWN;
        }
    }

    public Direction getDirection() {
        Random random = new Random();
        System.out.println(player.getX() + " " + enemy.getX());
        if(random.nextInt(2) == 0) return diNgang();
        return diDoc();

    }

}
