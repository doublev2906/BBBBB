package com.example.bbbbb.enemy;

import com.example.bbbbb.Bomber;
import com.example.bbbbb.Direction;

import java.util.Random;

public class EnemyAI {

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
