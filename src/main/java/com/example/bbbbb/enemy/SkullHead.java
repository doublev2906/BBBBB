package com.example.bbbbb.enemy;

import com.example.bbbbb.Board;
import com.example.bbbbb.Entity;
import com.example.bbbbb.Game;
import com.example.bbbbb.bomb.Bomb;
import com.example.bbbbb.sprite.MSprite;
import javafx.scene.canvas.GraphicsContext;

public class SkullHead extends Enemy {


    public SkullHead(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        toX = x;
        toY = y;

    }

    public void chooseImage() {
        if (left && isMoving) {
            image = MSprite.blockHeadLeft;
        } else if (right && isMoving) {
            image = MSprite.blockHeadRight;
        } else if (up && isMoving) {
            image = MSprite.blockHeadUp;
        } else if (down && isMoving) {
            image = MSprite.blockHeadDown;
        }
    }

    public void setSpeed(){
        switch (randomInt(0, 4)){
            case 1:
                speed = 1;
                break;
            case 2:
                speed = 2;
                break;
            default:
                break;
        }
    }

    public void chooseDirection() {
        if (!isMoving) {
            int x = randomInt(0, 6);
            switch (x) {
                case 1:
                    left = true;
                    right = false;
                    up = false;
                    down = false;
                    break;
                case 2:
                    left = false;
                    right = true;
                    up = false;
                    down = false;
                    break;
                case 3:
                    left = false;
                    right = false;
                    up = true;
                    down = false;
                    break;
                case 4:
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


    protected void calculateMove() {
        if (!isMoving) {
            if (up) {
                toY -= Game.ENTITY_SIZE;
            } else if (down) {
                toY += Game.ENTITY_SIZE;
            } else if (left) {
                toX -= Game.ENTITY_SIZE;
            } else if (right) {
                toX += Game.ENTITY_SIZE;
            }
        }

        if (left && canMove(x - 1, y)) {
            x-=speed;
            isMoving = true;
        } else if (right && canMove(x + 1, y)) {
            x+=speed;
            isMoving = true;
        } else if (up && canMove(x, y - 1)) {
            y-=speed;
            isMoving = true;
        } else if (down && canMove(x, y + 1)) {
            y+=speed;
            isMoving = true;
        } else {
            isMoving = false;
            toX = x;
            toY = y;
        }

        Bomb bomb = board.getBomb();
        if (bomb != null){
            System.out.println(bomb.getX()/32 +" "+ toX/32);
            if (toX/32 == bomb.getX()/32 && bomb.getY()/32 == toY/32) isMoving = false;
        }

        if (x == toX && y == toY) {
            isMoving = false;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        chooseImage();
        gc.drawImage(image, x, y, 32, 32);
    }

    @Override
    public void update() {
        setSpeed();
        chooseDirection();
        calculateMove();
        isKill();
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
