package com.example.bbbbb.enemy;

import com.example.bbbbb.Board;
import com.example.bbbbb.Direction;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Game;
import com.example.bbbbb.bomb.Bomb;
import com.example.bbbbb.sprite.MSprite;
import javafx.scene.canvas.GraphicsContext;

public class Needle extends Enemy{
    EnemyAI ai;

    public Needle(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        toX = x;
        toY = y;
        ai = new EnemyAI(board.getBomber(),this);
    }


    public void chooseDirection(){
        if (!isMoving){
            switch (ai.getDirection()){
                case LEFT:
                    left = true;
                    right = false;
                    up = false;
                    down = false;
                    break;
                case RIGHT:
                    left = false;
                    right = true;
                    up = false;
                    down = false;
                    break;
                case UP:
                    left = false;
                    right = false;
                    up = true;
                    down = false;
                    break;
                case DOWN:
                    left = false;
                    right = false;
                    up = false;
                    down = true;
                    break;
                default:
                    break;
            }
        }


    }

    public void calculateMove() {

        if (left && canMove(x - 1, y)) {
            x--;
            isMoving = true;
        } else if (right && canMove(x + 1, y)) {
            x++;
            isMoving = true;
        } else if (up && canMove(x, y - 1)) {
            y--;
            isMoving = true;
        } else if (down && canMove(x, y + 1)) {
            y++;
            isMoving = true;
        } else isMoving = false;

        Bomb bomb = board.getBomb();
        if (bomb != null){
            if (x/32 == bomb.getX()/32 && bomb.getY()/32 == y/32) isMoving = false;
        }

    }

    public void chooseImage() {
        if (left && isMoving) {
            image = MSprite.needleLeft;
        } else if (right && isMoving) {
            image = MSprite.needleRight;
        } else if (up && isMoving) {
            image = MSprite.needleUp;
        } else if (down && isMoving) {
            image = MSprite.needleDown;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseImage();
        gc.drawImage(image, x, y, 32, 32);
    }

    @Override
    public void update() {
        chooseDirection();
        calculateMove();
        isKill();
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
